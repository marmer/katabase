import now from "../../main/scripts/now";

describe("now", () => {
    it("should serve 'now'", async () => {
        //preparation
        const before = new Date().getTime()

        //execution
        const result = now();

        //assertions
        const after = new Date().getTime()
        expect(result.getTime()).toBeGreaterThanOrEqual(before)
        expect(result.getTime()).toBeLessThanOrEqual(after)
    });
});
