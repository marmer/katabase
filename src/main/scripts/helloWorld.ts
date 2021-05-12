export function umbrechen(text: string, maxLineLength: number): string {
  let spacesCleandText = text.replace(/\s+/g, " ").trim();
  return toLines(spacesCleandText, maxLineLength).join("\n");
}

function toLines(text: string, maxLineLength: number): string[] {
  let currentLine = text.substr(0, Math.min(text.length, maxLineLength)).trim();
  if (currentLine === "")
    return [];
  let followingLines = toLines(text.substr(maxLineLength), maxLineLength);
  return [currentLine].concat(followingLines);
}
