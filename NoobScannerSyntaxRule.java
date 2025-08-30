
import java.util.Stack;

class NoobScannerSyntaxRule{
    
    public boolean isClassDeclaration(String line) { // checks if it's a class declaration
        return line.matches("^\\s*(public|private|protected)?\\s*(class)\\s+\\w+.*");
    }

    public boolean isMethodDeclaration(String line) { // checks if a method is declared
        return line.matches("^\\s*(public|private|protected)?\\s*(static)?\\s*[\\w<>\\[\\]]+\\s+\\w+\\s*\\([^)]*\\)\\s*\\{?\\s*$");
    }

    public boolean isLoop(String line) { // checks for loop usage (for, while, do)
        return line.matches(".*\\b(for|while|do)\\b.*\\(.*\\).*");
    }

    public boolean isConditional(String line) { // checks for if, else if, else, or switch
        return line.matches(".*\\b(if|else if|else|switch)\\b.*");
    }

    public boolean isVariableDeclaration(String line) { // checks for variable declarations like int, float, etc.
        return line.matches("^\\s*[\\w<>\\[\\]]+\\s+\\w+\\s*(=\\s*[^;]+)?\\s*;$");
    }

    public boolean isPrintStatement(String line) { // checks for println statements
        return line.matches(".*System\\.out\\.println\\s*\\(.*\\)\\s*;");
    }

    public boolean isReturnStatement(String line) { // checks for return statement
        return line.matches(".*\\breturn\\b.*;");
    }

    public boolean isAccessModifierUsed(String line) { // checks if public, private, or protected is used
        return line.matches(".*\\b(public|private|protected)\\b.*");
    }

    public boolean isStaticKeywordUsed(String line) { // checks if 'static' keyword is used
        return line.matches(".*\\bstatic\\b.*");
    }

    public boolean isMainMethod(String line) { // checks for the main method
        return line.matches(".*\\bpublic\\s+static\\s+void\\s+main\\s*\\(.*String.*\\).*");
    }

    public boolean hasOpeningBrace(String line) { // checks for opening curly brace
        return line.contains("{");
    }

    public boolean hasClosingBrace(String line) { // checks for closing curly brace
        return line.contains("}");
    }

    public boolean endsWithSemicolon(String line) { // checks if line ends with semicolon
        return line.trim().endsWith(";");
    }

    public boolean containsClassDeclaration(String line) { // loosely checks for 'class' keyword
        return line.contains("class ");
    }

    public boolean containsMethodDeclaration(String line) { // checks if it looks like a method declaration
        return line.contains("(") && line.contains(")") && line.contains("{");
    }

    public boolean containsLoop(String line) { // checks if line has 'for' or 'while'
        return line.contains("for") || line.contains("while");
    }

    public boolean containsIfCondition(String line) { // checks if line has 'if' or 'else'
        return line.contains("if") || line.contains("else");
    }


    //Method Declaration for Braces Balancing

    Stack<Character> braceBalancer = new Stack<>();
    
    public boolean checkBraceBalance(String line, Stack<Character> braceBalancer){
        for(char c : line.toCharArray()){
            if(c == '{'){
                braceBalancer.push(c);
            }
            else if(c == '}'){
                if(braceBalancer.isEmpty()){
                    return false;
                }
                braceBalancer.pop();
            }
        }

        return braceBalancer.isEmpty();
    }

}