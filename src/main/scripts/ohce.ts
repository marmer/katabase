export type OutputProcessor = (input: string) => void
export type InputProvider = () => string

export class Ohce {
    private inputProvider: InputProvider;
    private outputProcessor: OutputProcessor;

    constructor(inputProvider: InputProvider, outputProcessor: OutputProcessor) {
        this.inputProvider = inputProvider;
        this.outputProcessor = outputProcessor;
    }

    private static isStopCommand(originalInput: string) {
        return originalInput === "Stop!";
    }

    public start(name: string): void {
        this.outputProcessor(`Hello ${name}`)

        let input = this.inputProvider();

        do {
            this.process(input, name);

            input = this.inputProvider()
        } while (!Ohce.isStopCommand(input))

    }

    private process(originalInput: string, name: string) {
        if (Ohce.isStopCommand(originalInput)) {
            this.outputProcessor(`Adios ${name}`)
        } else {
            let reverseInput = revert(originalInput);
            this.outputProcessor(reverseInput)
            if (reverseInput === originalInput) {
                this.outputProcessor("Bonita palabra!")
            }
        }
    }
}

const revert = (originalInput: string) => {
    return originalInput.split("").reverse().join("");
}
