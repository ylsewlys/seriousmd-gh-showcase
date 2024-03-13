import org.jpl7.Query;
import org.jpl7.Term;
import java.util.ArrayList;
import java.util.Map;


public class MedicalDiagnosis {
    

    private int age;
    private String patientName;
    private char gender;
    private ArrayList<String> patientSymptoms;
    private ArrayList<String> diagnosesList;
    private ArrayList<Float> diseaseWeights;


    
    public void printMedicalDiagnosis(){

        String strDisease;

        System.out.printf("\n----------------------------------------------");
        System.out.printf("\nMedical Record: ");

        System.out.printf("\n\nPatient Name: " + this.patientName);
        System.out.printf("\nPatient Age: " + this.age + " years old");
        System.out.printf("\nPatient Gender: ");

        if (gender == 'M' || gender == 'm'){
            System.out.printf("Male");
        } else if (gender == 'F' || gender == 'f'){
            System.out.printf("Female");
        }

        System.out.println("\n\nPatient's Symptoms: ");

        for (int i = 0; i < this.patientSymptoms.size(); i++){
            System.out.println("- " + this.patientSymptoms.get(i));
        }

        System.out.println("\n\nMedical Diagnosis: ");

        String format = "%-20s %5s\n";


        for (int i = 0; i < this.diagnosesList.size(); i++){

            strDisease = this.diagnosesList.get(i).replace('_', ' ');

            if (i == 0){
                System.out.println("Patient most likely has the disease " + strDisease + " with a score of " + this.diseaseWeights.get(i) + ".");

                System.out.println("\n\nTREATMENT: ");
                printTreatment(strDisease);

                if (this.diagnosesList.size() > 1){
                    System.out.println("\n\nThe following diseases are other possible diagnoses with their respective scores in descending order:");
                    System.out.format(format, "Disease:", " Score:");
                }


            }
            else{

                System.out.format(format, "- " + strDisease, this.diseaseWeights.get(i));
            }


        }










        System.out.printf("\n\nPatient Emergency Status: ");

        Query q1 = new Query("call_emergency");

        if (q1.hasSolution()){

            Query q2 = new Query("emergency(X)");

            Map<String, Term> sol = q2.next();
            System.out.printf("DECLARE EMERGENCY DUE TO " + (capitalizeWholeString(sol.get("X").toString())).replace('_', ' ') + "\n");
            q2.close();

        }
        else{
            System.out.printf("None");
        }
        
        q1.close();
        




        System.out.printf("\n----------------------------------------------\n\n");





    }



    public MedicalDiagnosis(int age, String patientName, char gender, ArrayList<String> patientSymptoms, ArrayList<String> diagnosesList, ArrayList<Float> diseaseWeights){
        this.age = age;
        this.patientName = patientName;
        this.gender = gender;
        this.patientSymptoms = patientSymptoms;
        this.diagnosesList = diagnosesList;
        this.diseaseWeights = diseaseWeights;

    }


    public void printTreatment(String disease){

        switch(disease){
            case "Dengue":
                System.out.println("- Dengue does not have specific treatment, instead the pain symptoms are treated.");
                System.out.println("- Acetaminophen (paracetamol) is often used to control pain. Avoid using non-steroidal anti-inflammatory drugs like ibuprofen and aspirin as they can increase the risk of bleeding.");
                System.out.println("- For people who have already gotten dengue, to avoid getting it again there is a vaccine called Dengvaxia");
                System.out.println("- Hospitalization is needed for those with severe dengue");
                break;

            case "Hypertension":
                System.out.println("- Lower blood pressure by implementing lifestyle changes");
                System.out.println("     1. Eat food with less salt");
                System.out.println("     2. Lose weight");
                System.out.println("     3. Be physically active");
                System.out.println("     4. Stay away from tobacco");
                System.out.println("- There are several common blood pressure medicines: ");  
                System.out.println("     1. ACE inhibitors relax blood vessels and prevent kidney damage. Examples: enalapril, lisinopril");
                System.out.println("     2. Angiotensin-2 receptor blockers (ARBs) relax blood vessels and prevent kidney damage. Examples: losartan and telmisartan");
                System.out.println("     3. Calcium channel blockers relax blood vessels. Examples: amlodipine, felodipine");
                System.out.println("     4. Diuretics eliminate extra water from the body, lowering blood pressure. Examples: hydrochlorothiazide, chlorthalidone");
                System.out.println("- Blood pressure goal is less than 140/90 (less than 130/80 if you have cardiovascular disease, diabetes, or a kidney disease)");  
                break;

            case "Hepatitis b":
                System.out.println("- For short-term hepatitis B, get a good amount of rest, nutrition, and fluids");
                System.out.println("- Those with severe symptoms need to be hospitalized");      
                System.out.println("- For chronic hepatitis B, there are several drugs and medications that can be taken, but due to their side effects, it is better to consult a doctor. ");
                System.out.println("- Get vaccinated for hepatitis A and get tested for hepatitis C");
                System.out.println("- Avoid drinking alcohol");
                break;

            case "Influenza":
                System.out.println("- Antiviral drugs are an effective treatment option, most effective when taken one to two days after the symptoms begin.");
                System.out.println("- Contact your doctor immediately if you are a young child, 65 years of age and older, pregnant, or have certain medical conditions\nsuch as asthma, diabetes and heart disease.");      
                break;         

            case "Cholera":
                System.out.println("- Rehydration: temporarily replace lost body fluid by giving Oral Rehydration Solution (ORESOL) or a homemade solution composed of 1 teaspoon of salt, 4 teaspoons of sugar mixed to 1 liter of water. ");
                System.out.println("- Rehydration is the most important treatment for cholera, do not provide drinks with high sugar content such as juice, soft drinks, or sports drinks, as it could worsen diarrhea.");
                break;

            case "Measles":
                System.out.println("- No specific antiviral treatment exists for measles virus. Get the vaccine to prevent getting it in the future");
                System.out.println("- Drink plenty of water and get a lot of rest to let the virus run its course");
                System.out.println("- Severe complications from measles can be reduced through supportive care that ensures good nutrition, adequate fluid intake and treatment of dehydration with WHO-recommended oral rehydration solution.");
                break;

            case "Tuberculosis":
                System.out.println("There are various treatments from latent and active infections, to specific people with HIV, pregnant, and for children.\nThus we urge you to coordinate with your local health professionals for treatment as it takes a long time before one can be treated, as it could be fatal if not treated properly.");
                System.out.println("To assist, here is a website that show facilities throughout the Philippines that is part of the national tuberculosis control program that might assist you in finding local help: https://ntp.doh.gov.ph/resources/facilities/ ");
                System.out.println("In addition, please protect others through protecting yourself as well through isolation, facemasks, and proper ventilation in your environment. While taking the treatment program, please take drugs as directed with discipline");
                System.out.println("When an emergency rises, please contact medical services.");
                break;

            case "Typhoid":
                System.out.println("Antibiotic therapy is the only effective treatment for typhoid fever (Mayo Clinic, 2023).");
                System.out.println("If you start to develop symptoms for typhoid fever, immediately see your doctor for proper diagnosis and treatment. Doctors often prescribe antibiotics for the treatment of typhoid fever.");
                System.out.println("Hospitalization is recommended for those who develop severe symptoms, such as severe diarrhea, a swollen stomach, or persistent vomiting, as well as for children who develop the sickness.");   
                break;

            case "Pneumonia":
                System.out.println("Treatment will depend on the cause for the pneumonia\n- Bacterial - Antibiotics\n- Fungal - Antifungal medication");
                System.out.println("Viral - A doctor would prescribe medication, but it can go away on its own. Alternatively, a provider can prescribe antivirals to reduce the severity of the virus and its duration.");
                System.out.println("Over-the-counter medicines may be recommended to treat fever and muscle pain or help you breathe easier. Talk to your provider before taking cough or cold medicines. (National Heart, Lung, and Blood Institute, 2022)");

                break;

            case "Leprosy":
                System.out.println("Treatment lasts usually for one to two years, it can be cured if treatment is completed as prescribed. Coordinate with your local health professionals for treatment, usually it is treated with a combination of antibiotics to prevent antibiotic resistance development of the bacteria.");
                System.out.println("Make sure to communicate with your doctor to share information on your situation from experiencing numbness or loss of feeling in certain parts of your body");
                System.out.println("Stick with the antibiotics and don't stop until your doctor concludes that your treatment is complete. Stopping early may make the bacteria start growing again.");
                break;


        }




    }


    // METHODS

    public String capitalize(String str){

        String string = str;
        StringBuilder sb = new StringBuilder(string);

        char temp = string.charAt(0);
        int nTemp = (int)temp;
        nTemp = nTemp - 32;
        temp = (char)nTemp;

        sb.setCharAt(0, temp);

        return sb.toString();

    }

    public String capitalizeWholeString(String str){

        String string = str;
        StringBuilder sb = new StringBuilder(string);


        
        for(int i = 0; i < str.length(); i++){

            if(!(sb.charAt(i) == '_')){
                char temp = string.charAt(i);
                int nTemp = (int)temp;
                nTemp = nTemp - 32;
                temp = (char)nTemp;
    
                sb.setCharAt(i, temp);
            }

        }


        return sb.toString();

    }

    // GETTERS

    public String getPatientName(){
        return this.patientName;
    }

    public int getPatientAge(){
        return this.age;
    }

    public char getPatientGender(){
        return this.gender;
    }

    public ArrayList<String> getPatientSymptoms(){
        return this.patientSymptoms;
    }

    public ArrayList<String> getDiagnosesList(){
        return this.diagnosesList;
    }

    public ArrayList<Float> getDiseaseWeights(){
        return this.diseaseWeights;
    }




}
