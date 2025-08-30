import java.io.*;
import java.util.*;
class NoobScannerFileParser{

    private String validateJavaFile(File file){
        if(!file.exists()){
            return "The file doesn't exist!";
        }
        if(!file.getName().endsWith(".java")){
            return "The file inputted is not a valid Java File!";
        }
        return null;  

    }
        public void readJavaFile(){
            Scanner sc = new Scanner(System.in);
        try{
            System.out.println("Enter the path of the Java File: ");
            String path = sc.nextLine();
            File file = new File(path);
            String validation = validateJavaFile(file);
            if(validation != null){
                System.out.println(validation);
                return;
            }


            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
            System.out.println("Do you wish to continue with syntax analysis? (Y/N)");
            String input = sc.nextLine();
            if(input.trim().equalsIgnoreCase("Y")){
                NoobScannerAnalyzer scanner = new NoobScannerAnalyzer();
                scanner.analyze(file);
            }
            else{
                System.out.println("Analysis Process - Aborted. (Use 'Cmd/Ctrl + C to quit process')");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            sc.close();
        }
    }
}

