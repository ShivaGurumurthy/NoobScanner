import java.io.*;

class NoobScannerAnalyzer{

    private boolean hasMethod = false;
    private boolean hasClass = false;
    // Tracks whether any conditional statements (if, else, switch) were found
    private boolean hasConditionals = false;
    private boolean hasLoop = false;
    private boolean hasPrintStatement = false;
    private boolean hasReturnStatement = false;

    NoobScannerSyntaxRule rules = new NoobScannerSyntaxRule();

    public void analyze(File file){
        System.out.println("Analyzing file:" + file.getName());
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int lineNumber = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String currentLine = line.trim();

                // Check 1: Missing both opening brace and semicolon
                if (!rules.hasOpeningBrace(currentLine) && !rules.endsWithSemicolon(currentLine)) {
                    System.out.println("[Line " + lineNumber + "] ⚠ Warning: Missing opening brace and semicolon -> " + currentLine);
                }

                // Check 2: Missing only opening brace
                if (!rules.hasOpeningBrace(currentLine) && rules.endsWithSemicolon(currentLine)) {
                    System.out.println("[Line " + lineNumber + "] ⚠ Warning: Missing opening brace -> " + currentLine);
                }

                // Check 3: Missing only semicolon
                if (rules.hasOpeningBrace(currentLine) && !rules.endsWithSemicolon(currentLine)) {
                    System.out.println("[Line " + lineNumber + "] ⚠ Warning: Missing semicolon -> " + currentLine);
                }

                // Optional Check 4: Line is too short or suspiciously empty
                if (currentLine.length() < 2) {
                    System.out.println("[Line " + lineNumber + "] ⚠ Warning: Empty or too short line -> \"" + currentLine + "\"");
                }

                if (rules.isClassDeclaration(currentLine)) {
                    hasClass = true;
                }

                if (rules.isMethodDeclaration(currentLine)) {
                    if (!hasClass) {
                        System.out.println("[Line " + lineNumber + "] ⚠ Warning: Method declared before class declaration -> " + currentLine);
                    }
                    hasMethod = true;
                }

                if (rules.isConditional(currentLine)) {
                    hasConditionals = true;
                }

                if (rules.containsLoop(currentLine)) {
                    hasLoop = true;
                }

                if (rules.isPrintStatement(currentLine)) {
                    hasPrintStatement = true;
                }

                if (rules.isReturnStatement(currentLine)) {
                    hasReturnStatement = true;
                }
                if(!rules.checkBraceBalance(currentLine, rules.braceBalancer)){
                    System.out.println("[Line " + lineNumber + "] ⚠ Warning: Extra closing brace detected!");
}
                }
            reader.close(); // Optionally close the reader

            System.out.println("===== Summary of Report =====");

            if (!hasClass) {
                System.out.println("⚠ Warning: No class declaration found in the file!");
            }

            if (!hasMethod) {
                System.out.println("⚠ Warning: No method declaration found in the file!");
            }

            if (!hasConditionals) {
                System.out.println("⚠ Warning: No conditionals found in the file!");
            }

            if (!hasLoop) {
                System.out.println("⚠ Warning: No loops found in the file!");
            }

            if (!hasPrintStatement) {
                System.out.println("⚠ Warning: No print statements found in the file!");
            }

            if (!hasReturnStatement) {
                System.out.println("⚠ Warning: No return statements found in the file!");
            }

            System.out.println("===== End of Summary Report =====");

            if(!rules.braceBalancer.isEmpty()){
                System.out.println("⚠ Warning: Unmatched opening braces detected!");
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}