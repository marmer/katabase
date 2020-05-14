package io.github.marmer;

import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class Bowling {
    public static int wurfeToScore(@NotNull final String eingabe) {
        final var frameBuilder = Frame.builder();

        eingabe.chars().forEach(wurf -> frameBuilder.addWurf((char) wurf));

        return frameBuilder.build().stream().mapToInt(Frame::getPunkte).sum();
    }

    interface Wurf {
        int getBasispunkte();

        int getExtrapunkte();

        default int getPunkte() {
            return getBasispunkte() + getExtrapunkte();
        }
    }

    @Value
    public static class Frame {
        List<Wurf> wuerfe;

        public Frame() {
            wuerfe = new ArrayList<>();
        }

        public Frame(final List<Wurf> wuerfe) {
            this.wuerfe = wuerfe;
        }

        public static Builder builder() {
            return new Builder();
        }

        public int getPunkte() {
            return wuerfe.stream().mapToInt(Wurf::getPunkte).sum();
        }

        public static class Builder {
            private final List<Wurf> wurfContext = new ArrayList<>();
            private Spiel spiel = new Spiel();

            public void addWurf(final char input) {
                final var wurf = toWurf(input);

                wurfContext.add(wurf);

                spiel.add(wurf);
            }

            private Wurf toWurf(final char input) {
                final var inEndgame = spiel.isEndspiel();
                final var currentWurfIndex = wurfContext.size();

                if (isStrike(input)) {
                    return new Strike(() -> Optional.ofNullable(inEndgame ? null : wurfContext.get(currentWurfIndex + 1)),
                            () -> Optional.ofNullable(inEndgame ? null : wurfContext.get(currentWurfIndex + 2)));
                } else if (isSpare(input)) {
                    return new Spare(() -> wurfContext.get(currentWurfIndex - 1),
                            () -> Optional.ofNullable(inEndgame ? null : wurfContext.get(currentWurfIndex + 1)));
                } else if (isPoints(input)) {
                    return new Points(Character.getNumericValue(input));
                } else {
                    return new Points(0);
                }
            }

            private boolean isPoints(final char wurf) {
                return Character.isDigit(wurf);
            }

            private boolean isSpare(final char wurf) {
                return wurf == '/';
            }

            private boolean isStrike(final char wurf) {
                return wurf == 'X';
            }

            public List<Frame> build() {
                final Spiel retVal = spiel;
                spiel = new Spiel();
                return retVal;
            }
        }
    }

    public static class Spiel extends ArrayList<Frame> {
        private static final long serialVersionUID = 258748164752144039L;

        public Spiel() {
            add(new Frame());
        }

        public boolean isEndspiel() {
            return size() >= 10;
        }

        public List<Wurf> currentFrameWuerfe() {
            return get(currentFrameIndex()).getWuerfe();
        }

        public boolean isReadyForNewFrame() {
            final List<Wurf> currentFrameWuerfe = currentFrameWuerfe();
            return !isEndspiel() &&
                    (currentFrameWuerfe.size() >= 2 || currentFrameWuerfe.get(currentFrameWuerfe.size() - 1) instanceof Strike);
        }

        private void add(final Wurf wurf) {
            final var wuerfe = new ArrayList<>(currentFrameWuerfe());
            wuerfe.add(wurf);
            set(currentFrameIndex(), new Frame(wuerfe));

            if (isReadyForNewFrame()) {
                add(new Frame());
            }
        }

        private int currentFrameIndex() {
            return size() - 1;
        }
    }

    @Value
    public static class Points implements Wurf {
        int basispunkte;

        @Override
        public int getExtrapunkte() {
            return 0;
        }
    }

    @Value
    public static class Spare implements Wurf {
        Supplier<Wurf> vorgaenger;
        Supplier<Optional<Wurf>> nachfolger;

        @Override
        public int getBasispunkte() {
            return 10 - vorgaenger.get().getBasispunkte();
        }

        @Override
        public int getExtrapunkte() {
            return getNachrfolgerPunkte();
        }

        @NotNull
        private Integer getNachrfolgerPunkte() {
            return this.nachfolger.get().map(Wurf::getBasispunkte).orElse(0);
        }

    }

    @Value
    public static class Strike implements Wurf {
        Supplier<Optional<Wurf>> nachfolger1;
        Supplier<Optional<Wurf>> nachfolger2;

        @Override
        public int getBasispunkte() {
            return 10;
        }

        @Override
        public int getExtrapunkte() {
            return getPunkteFrom(this.nachfolger1) +
                    getPunkteFrom(this.nachfolger2);
        }

        @NotNull
        private Integer getPunkteFrom(final Supplier<Optional<Wurf>> wurf) {
            return wurf.get().map(Wurf::getBasispunkte).orElse(0);
        }
    }
}
