import {umbrechen} from "../../main/scripts/helloWorld";

describe("application tests", () => {
  it("should nicht umbrechen, wenn eine laengere Zeilenlaenge als Woerter gegeben ist", async () => {
    expect(umbrechen("AaA BbB CcC", 11)).toStrictEqual("AaA BbB CcC")
  });

  it("should im Wort umbrechen, wenn eine kleinere Anzahl an Zeichen gegeben ist als der Text lang ist", async () => {
    expect(umbrechen("AaA BbB CcC", 6)).toStrictEqual(
        `AaA Bb
B CcC`)
  });

  it("should not contain unneccessary spaces", async () => {
    expect(umbrechen(`
          
        \t   AaA     \t BbB
         
         CcC   \t
         
         
         `, 6)).toStrictEqual(
        `AaA Bb
B CcC`)

  });
});
