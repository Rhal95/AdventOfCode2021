const fs = require("fs");

function openBrace(c) {
    return c === '(' || c === '[' || c === '{' || c === '<';
}

function closeBrace(c) {
    return c === ')' || c === ']' || c === '}' || c === '>';
}

function removeMatching(s) {
    let beforeReplacement = "";
    let afterReplacement = s;
    do {
        beforeReplacement = afterReplacement;
        afterReplacement = beforeReplacement.replace(/\(\)/g, "");
        afterReplacement = afterReplacement.replace(/\[]/g, "");
        afterReplacement = afterReplacement.replace(/{}/g, "");
        afterReplacement = afterReplacement.replace(/<>/g, "");
    } while (beforeReplacement !== afterReplacement);
    return afterReplacement;
}

function removeOpening(s) {
    return s.split("").filter(closeBrace).join("");
}

function valueOf(c) {
    return c === ')' ? 3 : c === ']' ? 57 : c === '}' ? 1197 : c === '>' ? 25137 : 0
}

const content = fs.readFileSync(process.argv[2] ?? "input", "utf8");
// console.log(content);
const lines = content.trim().split(/[\r\n]+/);

function getSyntaxErrorValue(lines) {
return lines
    .map(removeMatching)
    .map(removeOpening)
    .filter(s => s !== "")
    .map(s => s.charAt(0))
    .map(valueOf)
    .reduce((a, b) => a + b);
}

console.log(getSyntaxErrorValue(lines));

function getMiddleScore(lines){
    const lineScores = lines
        .map(removeMatching)
        .filter(s=>s.split("").every(openBrace))
        .map(line => {
            let score = 0;
            const len = line.length;
            for (let i = 0; i < len; i++) {
                switch (line.charAt(len-1-i)){
                    case '(': score = score * 5 + 1; break;
                    case '[': score = score * 5 + 2; break;
                    case '{': score = score * 5 + 3; break;
                    case '<': score = score * 5 + 4; break;
                }
            }
            return score;
        })
        .sort((a,b)=>a-b);
    return lineScores[Math.floor(lineScores.length/2)]
}

console.log(getMiddleScore(lines));
