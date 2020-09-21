export type Decimal = number
export type Roman = string

interface NumberMapping {
    decimal: Decimal
    roman: Roman
}

const NUMBER_MAPPINGS: Array<NumberMapping> = [
    {decimal: 900, roman: "CM"},
    {decimal: 1000, roman: "M"},
    {decimal: 400, roman: "CD"},
    {decimal: 500, roman: "D"},
    {decimal: 90, roman: "XC"},
    {decimal: 100, roman: "C"},
    {decimal: 40, roman: "XL"},
    {decimal: 50, roman: "L"},
    {decimal: 9, roman: "IX"},
    {decimal: 10, roman: "X"},
    {decimal: 4, roman: "IV"},
    {decimal: 5, roman: "V"},
    {decimal: 1, roman: "I"},
]

const byDecimal = (a: NumberMapping, b: NumberMapping) => a.decimal - b.decimal;

export const toRoman = (decimal: Decimal): Roman => {
    const mapping = [...NUMBER_MAPPINGS]
        .sort(byDecimal).reverse()
        .find(value => value.decimal <= decimal);

    return mapping ?
        mapping.roman + toRoman(decimal - mapping.decimal) :
        ""; //Break condition
}

export const toDecimal = (roman: Roman): Decimal => {
    const mapping = NUMBER_MAPPINGS
        .find(mapped => roman.includes(mapped.roman));

    return mapping ?
        mapping.decimal + toDecimal(roman.replace(mapping.roman, "")) :
        0; // break condition
}
