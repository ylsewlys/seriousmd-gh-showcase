import org.jpl7.Query;
import org.jpl7.Term;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;

public class App {

    public static void printMenu(){
        System.out.println("\n\n\n---------------------------");
        System.out.println("Medical Expert System");
        System.out.println("[1] Start Diagnosis");
        System.out.println("[2] Diseases and Symptoms List");
        System.out.println("[0] Exit");
        System.out.println("");
        System.out.printf("Enter your choice: ");
    }

    public static void printSymptoms(int length, ArrayList<String> symptomsList){

        for (int i = 0; i < length; i++){
            System.out.println("[" + (i + 1) + "] " + symptomsList.get(i));
        }

    }


    public static String getDiseasePlFormat(String disease){

        switch(disease){
            case "Dengue":
                return "dengue";

            case "Hypertension":
                return "hypertension";

            case "Hepatitis B":
                return "hepatitis_b";

            case "Influenza":
                return "influenza";

            case "Cholera":
                return "cholera";
            
        }


        return null;

    }

    public static int getDiseaseId(String disease){

        switch(disease){
            case "dengue":
                return 0;

            case "hypertension":
                return 1;

            case "hepatitis_b":
                return 2;

            case "influenza":
                return 3;

            case "cholera":
                return 4;

            case "measles":
                return 5;

            case "tuberculosis":
                return 6;

            case "typhoid":
                return 7;

            case "pneumonia":
                return 8;

            case "leprosy":
                return 9;

            default:
                return -1;


        }



    }

    public static String capitalize(String str){

        String string = str;
        StringBuilder sb = new StringBuilder(string);

        char temp = string.charAt(0);
        int nTemp = (int)temp;
        nTemp = nTemp - 32;
        temp = (char)nTemp;

        sb.setCharAt(0, temp);

        return sb.toString();

    }




    

    public static HashMap<String, Float> sortByValue(HashMap<String, Float> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Float> > list =
               new LinkedList<Map.Entry<String, Float> >(hm.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Float> >() {
            public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2){
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
         
        // put data from sorted list to hashmap
        HashMap<String, Float> temp = new LinkedHashMap<String, Float>();
        for (Map.Entry<String, Float> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
















    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        DiseasesList dl = new DiseasesList();


        int nMenuChoice = -1;
        int chiefSymptomChoice;

        
        ArrayList<Integer> chiefSymptomsList = new ArrayList<Integer>(); 

        ArrayList<String> symptomsList = new ArrayList<String>();
        ArrayList<String> symptomsPrologList = new ArrayList<String>();
        ArrayList<String> includeList = new ArrayList<String>();
        ArrayList<String> diseaseSymptoms = new ArrayList<String>();
        ArrayList<String> diagnosesList = new ArrayList<String>();
        ArrayList<Float> diseaseWeights = new ArrayList<Float>(); 

        String strQuery;
        String strDisease;
        String strSymptom;

        boolean consultResult;
        boolean hasSolution;

        boolean skip;


        // PATIENT INFORMATION
        String patientName;
        int age;
        char gender;

        // SYMPTOMS LOADER
        BufferedReader br;

        // QUERIES
        Query questionQuery, q3;

        // PATIENT CHOICE
        char answer;

        // FOR INCLUDE LIST
        String str;

        // DIAGNOSIS
        ArrayList<String> patientSymptoms = new ArrayList<String>();
        String diagnosedDisease;
        Float fWeight;

        







        while(!(nMenuChoice == 0)){

            printMenu();
            nMenuChoice = sc.nextInt();
    
            if (nMenuChoice < 0 || nMenuChoice > 2){
                while(nMenuChoice < 0 || nMenuChoice > 2){
                    System.out.printf("Invalid input! Please enter a valid option: ");
                    nMenuChoice = sc.nextInt();
                }
            }
    
            //dl.printDiseaseList();

            switch (nMenuChoice) {
                case 0: // EXIT
                    break;
                case 1: 
                    Query q1 = new Query("consult('src/mcokb.pl')");
                    consultResult = q1.hasSolution();



                    if (consultResult){
                        System.out.println("Medical knowledge base has successfully been consulted. Diagnosis may now commence.");

                        try {
                            // INITIALIZE LISTS 
                            
                            chiefSymptomsList = new ArrayList<Integer>();
                            symptomsList = new ArrayList<String>();
                            symptomsPrologList = new ArrayList<String>();
                            includeList = new ArrayList<String>();
                            diseaseSymptoms = new ArrayList<String>();
                            diagnosesList = new ArrayList<String>();
                            diseaseWeights = new ArrayList<Float>(); 
                            patientSymptoms = new ArrayList<String>();
                            
                            br = new BufferedReader(new FileReader("txtfiles/symptomslist.txt"));
                            String line = br.readLine();
                
                            while (line != null) {
                                symptomsList.add(line);
                                // read next line
                                line = br.readLine();
                            }
                
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                
                
                        try {
                            br = new BufferedReader(new FileReader("txtfiles/symptomsplconverted.txt"));
                            String line = br.readLine();
                
                            while (line != null) {
                                symptomsPrologList.add(line);
                                // read next line
                                line = br.readLine();
                            }
                
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    else{
                        System.out.println("Medical knowledge base has not been consulted. Please ensure that the source files are correctly placed in their directories.\nGoing back to the menu...");
                    }

                    sc = new Scanner(System.in);

                    System.out.printf("\nWhat is your name? ");
                    patientName = sc.nextLine();

                    System.out.printf("What is your age? ");
                    age = sc.nextInt();

                    System.out.printf("What is your gender? [M]ale or [F]emale: ");
                    gender = sc.next().charAt(0);


                    if (!(gender == 'M' || gender == 'm' || gender == 'F' || gender == 'f')){

                        while(!(gender == 'M' || gender == 'm' || gender == 'F' || gender == 'f')){
                            System.out.printf("\nPlease enter a valid input. ");
                            gender = sc.next().charAt(0);

                        }
                    }









                    System.out.printf("\n\nWhat’s your reason for seeking medical diagnosis? Are you experiencing any symptom/s?\nIf so, please choose among the symptoms in this list that mainly led you to seek medical diagnosis.\n\nSymptoms: \n");

                    printSymptoms(symptomsList.size(), symptomsList);
                    
                    System.out.println("Please enter each of the symptom number you are currently experiencing. Choose 0 to finish input.\nInput: ");

                    chiefSymptomChoice = -1;
                    while (!(chiefSymptomChoice == 0)){
                        chiefSymptomChoice = sc.nextInt();

                        if (chiefSymptomChoice > 60 || chiefSymptomChoice < 0){
                            System.out.println("Please enter a valid input. ");
                        }
                        else if (chiefSymptomsList.contains(chiefSymptomChoice)){
                            System.out.println("You've already chosen this symptom. Please enter another valid input. ");
                        }
                        else if (!(chiefSymptomChoice == 0)){
                            chiefSymptomsList.add(chiefSymptomChoice);
                            patientSymptoms.add(symptomsList.get(chiefSymptomChoice - 1));
                            strQuery = "assert(yes(" + symptomsPrologList.get(chiefSymptomChoice - 1) + "))";
                            q1 = new Query(strQuery);                 
                            q1.hasSolution();
                            q1 = new Query("assert(asked(" + symptomsPrologList.get(chiefSymptomChoice - 1) + "))");
                            q1.hasSolution();

                        }

                    }

                    System.out.println("\nPatient's Chief Symptoms: ");


                    for (int i = 0; i < chiefSymptomsList.size(); i++){
                        System.out.println("- " + symptomsList.get(chiefSymptomsList.get(i) - 1));
                    }


                    q1 = new Query("include(X)");

                    int temp = 0;
                    System.out.println("\n\nPossible disease/s:");

                    while(q1.hasMoreSolutions()){
                        Map<String, Term> sol = q1.next();
                        str = sol.get("X").toString();



                        if (!(includeList.contains(str))){
                            includeList.add(str);
                            
                            Query possibleQuery = new Query("assert(possible(" + includeList.get(temp) + "))");
                            possibleQuery.hasSolution();
                            possibleQuery.close();

                            System.out.println("- " + capitalize(includeList.get(temp)).replace('_', ' '));

                            
                            temp++;
                        }
                      

                    }




                    // ASK QUESTIONS PART

                    if (includeList.size() > 0){    // If there are possible diseases

                        for (int i = 0; i < includeList.size(); i++){
                            strDisease = includeList.get(i);
                            System.out.println("\nQuestions for " + capitalize(strDisease).replace('_', ' '));

                            diseaseSymptoms = dl.getDiseaseList().get(getDiseaseId(strDisease)).getDiseaseSymptoms();


                            // This code segment skips asking the emergency symptoms of hypertensive crisis if blood pressure is not higher than 180/120 mm Hg.
                            skip = false;

                            if (strDisease.compareTo("hypertension") == 0){
                                q3 = new Query("yes(too_high_blood_pressure)");
                                hasSolution = q3.hasSolution();

                                if (hasSolution == false){
                                    skip = true;
                                }

                                q3.close();
                            }


                            // If skip is false, continue looping through the disease's symptoms
                            
                            if (skip == false){
                                for (int j = 0; j < diseaseSymptoms.size(); j++){
                                    questionQuery = new Query("asked(" + diseaseSymptoms.get(j) + ")");
                                    //System.out.println("asked(" + diseaseSymptoms.get(j) + ") is " + (questionQuery.hasSolution() ? "provable" : "not provable"));
    
                                    if (questionQuery.hasSolution() == false){
    
                                        strSymptom = diseaseSymptoms.get(j);
                                        System.out.printf("Does the patient have " + strSymptom.replace('_', ' ') + "? If yes, input [Y]: ");
                                        sc = new Scanner(System.in);
                                        answer = sc.next().charAt(0);
    
                                        if (answer == 'y'){
                                            q1 = new Query("assert(yes(" + diseaseSymptoms.get(j) + "))");
                                            q1.hasSolution();
    
                                            patientSymptoms.add(strSymptom.replace('_', ' '));
    
                                        }
    
                                        q1 = new Query("assert(asked(" + diseaseSymptoms.get(j) + "))");
                                        q1.hasSolution();
    
                                    }
    
                                }
                          
       

                            }



                        }

                        q1.close();

                        HashMap<String, Float> diagnoseMap = new HashMap<>();

                        q1 = new Query("diagnose(X, Score)");

                        if (q1.hasMoreSolutions()){

                            q1.close();

                            Query q2 = new Query("diagnose(X, Score)");

                            Map<String, Term>[] diagnoseSolution = q2.allSolutions();
                            for (int i = 0; i < diagnoseSolution.length; i++){
    
                                diagnosedDisease = capitalize(diagnoseSolution[i].get("X").toString());
                                fWeight = Float.parseFloat(diagnoseSolution[i].get("Score").toString());
    
                                diagnoseMap.put(diagnosedDisease, fWeight);
        
                            }
    
                            Map<String, Float> hm1 = sortByValue(diagnoseMap);
    
                            for (Map.Entry<String, Float> en : hm1.entrySet()) {
                                diagnosesList.add(en.getKey());
                                diseaseWeights.add(en.getValue());
                            }
    
    
    


                            MedicalDiagnosis md = new MedicalDiagnosis(age, patientName, gender, patientSymptoms, diagnosesList, diseaseWeights);


                            md.printMedicalDiagnosis();


                        }else{
                            System.out.println("\n\nThere is no diagnosed disease.");
                        }



                    }else{
                        System.out.println("Please input at least one main symptom to begin diagnosis.");
                    }

                    q1 = new Query("undo");
                    q1.hasSolution();
                    q1.close();

                    break;

                    
                case 2: // PRINT DISEASES AND SYMPTOMS
                    dl.printDengue();
                    dl.printHypertension();
                    dl.printHepatitis();
                    dl.printInfluenza();
                    dl.printCholera();
                    dl.printMeasles();
                    dl.printTuberculosis();
                    dl.printTyphoid();
                    dl.printPneumonia();
                    dl.printLeprosy();
                    break;
                case 3:
                    break;
    
                default:
                    System.out.println("Invalid input!");
                    break;
            }
    
        }
      
        sc.close();
        
        
        System.out.println("Exiting system...");


    }
}
