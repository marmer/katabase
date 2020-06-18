import getGreetingsFor from "../../main/scripts/greetings";

jest.mock("../../main/scripts/now", () => () => nowMock)

let nowMock: Date

function getTime(hours: number, minutes: number, seconds: number, millis: number) {
    return new Date(2020, 6, 18, hours, minutes, seconds, millis);
}

describe("GreetingsProvider", () => {
    describe("getGreetingsFor", () => {

        ([{
            time: getTime(6, 0, 0, 0),
            name: "Someone",
            expectedGreeting:"Buenos dias Someone"
        },{
            time: getTime(7, 0, 0, 0),
            name: "Someone",
            expectedGreeting:"Buenos dias Someone"
        },{
            time: getTime(11, 59, 59, 999),
            name: "Someone",
            expectedGreeting:"Buenos dias Someone"
        },{
            time: getTime(12, 0, 0, 0),
            name: "Someone",
            expectedGreeting:"Buenos tarde Someone"
        },{
            time: getTime(13, 0, 0, 0),
            name: "Someone",
            expectedGreeting:"Buenos tarde Someone"
        },{
            time: getTime(19, 59, 59, 999),
            name: "Someone",
            expectedGreeting:"Buenos tarde Someone"
        },{
            time: getTime(20, 0, 0, 0),
            name: "Someone",
            expectedGreeting:"Buenos noche Someone"
        },{
            time: getTime(0, 0, 0, 0),
            name: "Someone",
            expectedGreeting:"Buenos noche Someone"
        },{
            time: getTime(5, 59, 59, 999),
            name: "Someone",
            expectedGreeting:"Buenos noche Someone"
        }] as Array<{ time: Date, name: string, expectedGreeting: string }>)
            .forEach(({time, name, expectedGreeting}) => it("should serve the correct greeting at the beginning of the morning", async () => {
                //preparation
                nowMock = time

                //execution
                const result = getGreetingsFor(name);

                //assertions
                expect(result).toStrictEqual(expectedGreeting)
            }))
    });
});
