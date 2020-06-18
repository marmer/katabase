import now from "./now";

export type GreetingProvider = (name: string) => string

let MORNING_TIME: Date = new Date(2000, 1, 1, 6, 0, 0, 0);
let NOON_TIME: Date = new Date(2000, 1, 1, 12, 0, 0, 0);
let NIGHT_TIME: Date = new Date(2000, 1, 1, 20, 0, 0, 0);

const normalizedTimeOf: (date: Date) => Date = (date: Date) => {
    const normalizedDate = new Date(date)
    normalizedDate.setFullYear(2000, 1, 1)
    return normalizedDate
}

function isNowBetween(startTimeInclusive: Date, endTimeExclusive: Date) {
    const normalized = normalizedTimeOf(now());
    return normalized.getTime() >= startTimeInclusive.getTime() && normalized.getTime() < endTimeExclusive.getTime();
}

function isMorningTime() {
    return isNowBetween(MORNING_TIME, NOON_TIME);
}

function isNoonTime() {
    return isNowBetween(NOON_TIME, NIGHT_TIME);
}

const getGreetingsFor: GreetingProvider = (name: string) => {
    if (isMorningTime())
        return `Buenos dias ${name}`
    else if (isNoonTime())
        return `Buenos tarde ${name}`
    else
        return `Buenos noche ${name}`
}

export default getGreetingsFor
