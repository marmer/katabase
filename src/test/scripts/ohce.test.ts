import {InputProvider, Ohce, OutputProcessor} from "../../main/scripts/ohce";
import {GreetingProvider} from "../../main/scripts/greetings";

describe("ohce", () => {
    let inputs: string[]
    let outputs: string[]
    let inputProviderMock: InputProvider = jest.fn().mockImplementation(() => inputs.length === 0 ? "Stop!" : inputs.shift())
    let outputProcessorMock: OutputProcessor = jest.fn().mockImplementation((output: string) => outputs.push(output))
    let greetingProviderMock: GreetingProvider = jest.fn().mockImplementation((name: string) => `Hello ${name}`)

    let underTest: Ohce = new Ohce(inputProviderMock, outputProcessorMock, greetingProviderMock)

    beforeEach(() => {
        outputs = []
    });

    it("should revert the given input", async () => {
        //preparation
        inputs = ["Schuetze"]

        //execution
        underTest.start("Someone")

        //assertions
        expect(outputs).toContain("ezteuhcS")
    });

    it("should be possible to process multiple inputs", async () => {
        //preparation
        inputs = ["far", "away"]

        //execution
        underTest.start("Someone")

        //assertions
        expect(outputs).toContain("raf")
        expect(outputs).toContain("yawa")
    });

    it("should stop with a good bye message and a name at the stop command", async () => {
        //preparation
        inputs = ["Stop!"]

        //execution
        underTest.start("Superman")

        //assertions
        expect(outputs).toContain("Adios Superman")
    });

    it("should say something nice at palindromes", async () => {
        //preparation
        inputs = ["anna"]

        //execution
        underTest.start("Superman")

        //assertions
        expect(outputs).toContain("Bonita palabra!")
    });

    it("should greet the user with its name at start", async () => {
        //preparation
        inputs = ["Stop!"]

        //execution
        underTest.start("Superman")

        //assertions
        expect(outputs).toContain("Hello Superman")
    });
});
