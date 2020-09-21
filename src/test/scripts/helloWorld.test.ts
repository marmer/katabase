import {Decimal, Roman, toDecimal, toRoman} from "../../main/scripts/romanNumbers";


describe("application tests", () => {
    describe("decimal to roman", () => {
        describe("Simple Mapping", () => {
            ([
                {decimal: 1, roman: "I"},
                {decimal: 5, roman: "V"},
                {decimal: 10, roman: "X"},
                {decimal: 50, roman: "L"},
                {decimal: 100, roman: "C"},
                {decimal: 500, roman: "D"},
                {decimal: 1000, roman: "M"},
            ] as Array<{ decimal: Decimal, roman: Roman }>)
                .map(({decimal, roman}) => it(`${decimal} should map to ${roman}`, async () => {
                    expect(toRoman(decimal)).toStrictEqual(roman)
                }))
        })

        describe("Addition Rule", () => {
            ([
                {decimal: 3, roman: "III"},
                {decimal: 15, roman: "XV"},
                {decimal: 30, roman: "XXX"},
                {decimal: 150, roman: "CL"},
                {decimal: 300, roman: "CCC"},
                {decimal: 1500, roman: "MD"},
                {decimal: 3000, roman: "MMM"},
            ] as Array<{ decimal: Decimal, roman: Roman }>)
                .map(({decimal, roman}) => it(`${decimal} should map to ${roman}`, async () => {
                    expect(toRoman(decimal)).toStrictEqual(roman)
                }))
        })

        describe("Subtraction rule", () => {
            ([
                {decimal: 4, roman: "IV"},
                {decimal: 9, roman: "IX"},
                {decimal: 999, roman: "CMXCIX"},
            ] as Array<{ decimal: Decimal, roman: Roman }>)
                .map(({decimal, roman}) => it(`${decimal} should map to ${roman}`, async () => {
                    expect(toRoman(decimal)).toStrictEqual(roman)
                }))
        })

        describe("Acceptance", () => {
            ([
                {decimal: 1, roman: "I"},
                {decimal: 2, roman: "II"},
                {decimal: 4, roman: "IV"},
                {decimal: 5, roman: "V"},
                {decimal: 9, roman: "IX"},
                {decimal: 10, roman: "X"},
                {decimal: 42, roman: "XLII"},
                {decimal: 89, roman: "LXXXIX"},
                {decimal: 99, roman: "XCIX"},
                {decimal: 2013, roman: "MMXIII"},
            ] as Array<{ decimal: Decimal, roman: Roman }>)
                .map(({decimal, roman}) => it(`${decimal} should map to ${roman}`, async () => {
                    expect(toRoman(decimal)).toStrictEqual(roman)
                }))
        })

    })

    describe("roman to decimal", () => {
        describe("Simple Mapping", () => {
            ([
                {decimal: 1, roman: "I"},
                {decimal: 5, roman: "V"},
                {decimal: 10, roman: "X"},
                {decimal: 50, roman: "L"},
                {decimal: 100, roman: "C"},
                {decimal: 500, roman: "D"},
                {decimal: 1000, roman: "M"},
            ] as Array<{ decimal: Decimal, roman: Roman }>)
                .map(({decimal, roman}) => it(`${decimal} should map to ${roman}`, async () => {
                    expect(toDecimal(roman)).toStrictEqual(decimal)
                }))
        })

        describe("Addition Rule", () => {
            ([
                {decimal: 3, roman: "III"},
                {decimal: 15, roman: "XV"},
                {decimal: 30, roman: "XXX"},
                {decimal: 150, roman: "CL"},
                {decimal: 300, roman: "CCC"},
                {decimal: 1500, roman: "MD"},
                {decimal: 3000, roman: "MMM"},
            ] as Array<{ decimal: Decimal, roman: Roman }>)
                .map(({decimal, roman}) => it(`${decimal} should map to ${roman}`, async () => {
                    expect(toDecimal(roman)).toStrictEqual(decimal)
                }))
        })

        describe("Subtraction rule", () => {
            ([
                {decimal: 4, roman: "IV"},
                {decimal: 9, roman: "IX"},
                {decimal: 999, roman: "CMXCIX"},
            ] as Array<{ decimal: Decimal, roman: Roman }>)
                .map(({decimal, roman}) => it(`${decimal} should map to ${roman}`, async () => {
                    expect(toDecimal(roman)).toStrictEqual(decimal)
                }))
        })

        describe("Acceptance", () => {
            ([
                {decimal: 1, roman: "I"},
                {decimal: 2, roman: "II"},
                {decimal: 4, roman: "IV"},
                {decimal: 5, roman: "V"},
                {decimal: 9, roman: "IX"},
                {decimal: 10, roman: "X"},
                {decimal: 42, roman: "XLII"},
                {decimal: 89, roman: "LXXXIX"},
                {decimal: 99, roman: "XCIX"},
                {decimal: 2013, roman: "MMXIII"},
            ] as Array<{ decimal: Decimal, roman: Roman }>)
                .map(({decimal, roman}) => it(`${decimal} should map to ${roman}`, async () => {
                    expect(toDecimal(roman)).toStrictEqual(decimal)
                }))
        })

    });
});
