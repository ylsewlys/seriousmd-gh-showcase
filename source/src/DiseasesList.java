import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class DiseasesList {

    private ArrayList<Disease> diseaseList = new ArrayList<Disease>();






    public void printDiseaseList(){
        for (int i = 0; i < diseaseList.size(); i++){
            System.out.println("Disease: " + diseaseList.get(i).getDiseaseName());
            System.out.println("Symptoms: ");
            for (int j = 0; j < diseaseList.get(i).getDiseaseSymptoms().size(); j++){
                System.out.println("[" + j + "] " + diseaseList.get(i).getDiseaseSymptoms().get(j));
            }
        }
    }

    public DiseasesList(){

        
        try{
            File f = new File("txtfiles/diseases.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br;
			br = new BufferedReader(new FileReader("txtfiles/diseases.txt"));

            String diseaseName;
            int diseasesCount;
            int symptomCount;
            ArrayList<String> symptomsList;

            diseasesCount = Integer.parseInt(br.readLine());


            for (int i = 0; i < diseasesCount; i++){

                symptomsList = new ArrayList<String>();

                diseaseName = br.readLine();
                symptomCount = Integer.parseInt(br.readLine());

                for (int j = 0; j < symptomCount; j++){
                    symptomsList.add(br.readLine());

                }

                this.diseaseList.add(new Disease(diseaseName, symptomsList));



            }

            br.close();

        }catch (IOException e){
            e.printStackTrace();
        }

        

    }

    public ArrayList<Disease> getDiseaseList(){
        return this.diseaseList;
    }





    public void printDengue(){
        System.out.println("\n1. Dengue - is a viral infection caused by the dengue virus (DENV), transmitted to humans through the bite of infected mosquitoes.");
        System.out.println("\n   Symptoms may include:");
        System.out.printf("     - High Fever (40°C/104°F) *");
        System.out.printf("\n     - Severe headaches");
        System.out.printf("\n     - Pain behind the eyes");
        System.out.printf("\n     - Muscle and joint pains");
        System.out.printf("\n     - Nausea");
        System.out.printf("\n     - Vomiting");
        System.out.printf("\n     - Swollen Glands");
        System.out.printf("\n     - Rash");
    }


    public void printHypertension(){
        System.out.println("\n\n2. Hypertension - high blood pressure, also called hypertension, is blood pressure that is higher than normal. Your blood pressure changes throughout the day based on your activities.\n   Having blood pressure measures consistently above normal may result in a diagnosis of high blood pressure (or hypertension).");
        System.out.println("\n   Symptoms may include:");
        System.out.printf("     - High blood pressure (140/90 mmHg or higher).");
        System.out.printf("\n     - Declare emergency if blood pressure is (180/120 mmHg or higher) and any of the following symptoms are felt:");
        System.out.printf("\n          -> Severe headaches");
        System.out.printf("\n          -> Chest pain");
        System.out.printf("\n          -> Dizziness");
        System.out.printf("\n          -> Difficulty Breathing");
        System.out.printf("\n          -> Nausea");
        System.out.printf("\n          -> Vomiting");
        System.out.printf("\n          -> Vision changes");
        System.out.printf("\n          -> Anxiety");
        System.out.printf("\n          -> Confusion");
        System.out.printf("\n          -> Ear buzzings");
        System.out.printf("\n          -> Nosebleed");
        System.out.printf("\n          -> Abnormal heart rhythm");
    }


    public void printHepatitis(){
        System.out.println("\n\n3. Hepatitis B - is a potentially life-threatening liver infection caused by the hepatitis B virus (HBV). It is a major global health problem.\n   It can cause chronic infection and puts people at high risk of death from cirrhosis and liver cancer.");
        System.out.println("\n   Symptoms may include:");
        System.out.printf("     - Fever");
        System.out.printf("\n     - Fatigue");
        System.out.printf("\n     - Loss of appetite");
        System.out.printf("\n     - Nausea");
        System.out.printf("\n     - Vomiting");
        System.out.printf("\n     - Abdomninal pain");
        System.out.printf("\n     - Dark urine");
        System.out.printf("\n     - Clay-colored bowel movements");
        System.out.printf("\n     - Joint pain");
        System.out.printf("\n     - Jaundice");
    }

    public void printInfluenza(){
        System.out.println("\n\n4. Influenza - Influenza (flu) is a contagious respiratory illness caused by influenza viruses that infect the nose, throat, and lungs.");
        System.out.println("\n   Symptoms may include:");
        System.out.printf("     - Fever or feeling feverish/chills");
        System.out.printf("\n     - Cough");
        System.out.printf("\n     - Sore throat");
        System.out.printf("\n     - Runny or stuffy nose");
        System.out.printf("\n     - Muscle or body aches");
        System.out.printf("\n     - Headaches");
        System.out.printf("\n     - Fatigue ");
        System.out.printf("\n     - May include vomiting or diarrhea");
    }

    public void printCholera(){
        System.out.println("\n\n5. Cholera - is an acute diarrheal illness caused by infection of the intestine with Vibrio cholerae bacteria.");
        System.out.println("\n   Symptoms may include:");
        System.out.printf("     - Vomiting");
        System.out.printf("\n     - Dehydration");
        System.out.printf("\n     - Rapid heart rate");
        System.out.printf("\n     - Low blood pressure");
        System.out.printf("\n     - Muscle cramps");
        System.out.printf("\n     - Thirst");
        System.out.printf("\n     - Fatigue ");
    }


    public void printMeasles(){
        System.out.println("\n\n6. Measles - Measles is a viral respiratory illness caused by a single-stranded, enveloped RNA virus with 1 serotype. It is a highly contagious disease that can lead to serious complications.");
        System.out.println("\n   Symptoms may include:");
        System.out.printf("     - High Fever (40°C/104°F)");
        System.out.printf("\n     - Cough");
        System.out.printf("\n     - Runny nose");
        System.out.printf("\n     - Red, watery eyes");
        System.out.printf("\n     - Muscle cramps");
        System.out.printf("\n     - Koplik spots in the mouth (after 2-3 days)");
        System.out.printf("\n     - Measles rash (after 3-5 days) * ");
    }

    public void printTuberculosis(){
        System.out.println("\n\n7. Tuberculosis - a disease caused by germs that are spread from person to person through the air. TB usually affects the lungs, but it can also affect other parts of the body, such as the brain, the kidneys, or the spine. A person with TB can die if they do not get treatment.");
        System.out.println("\n   Symptoms may include:");
        System.out.printf("     - Fever");
        System.out.printf("\n     - Weight loss");
        System.out.printf("\n     - Night sweats");
        System.out.printf("\n     - Cough");
        System.out.printf("\n     - Chest pain");
        System.out.printf("\n     - Coughing blood");

    }

    public void printTyphoid(){
        System.out.println("\n\n8. Typhoid - typhoid fever is a bacterial infection caused by the bacterium Salmonella Typhi. It is typically spread through contaminated food and water, and is more common in areas with poor sanitation and hygiene");
        System.out.println("\n   Symptoms may include:");
        System.out.printf("     - Fever");
        System.out.printf("\n     - Headaches");
        System.out.printf("\n     - Abdominal pain");
        System.out.printf("\n     - Diarrhea");
        System.out.printf("\n     - Nausea");
        System.out.printf("\n     - Loss of appetite");
        System.out.printf("\n     - Weakness");
        System.out.printf("\n     - Rash");
        System.out.printf("\n     - Joint pain");
    }

    public void printPneumonia(){
        System.out.println("\n\n9. Pneumonia - pneumonia is an infection that causes inflammation in one or both lungs, typically caused by bacteria, viruses, or fungi. The infection causes air sacs in the lungs to fill with pus or other fluids, making it difficult to breathe.");
        System.out.println("\n   Symptoms may include:");
        System.out.printf("     - Fever");
        System.out.printf("\n     - Shortness of breath");
        System.out.printf("\n     - Chest pain");
        System.out.printf("\n     - Rapid heartbeat");
        System.out.printf("\n     - Loss of appetite");
        System.out.printf("\n     - Weakness");
        System.out.printf("\n     - Muscle or body aches");
        System.out.printf("\n     - Joint pain");
        System.out.printf("\n     - Cough");

    }

    public void printLeprosy(){
        System.out.println("\n\n10. Leprosy - Leprosy is an infection caused by bacteria called Mycobacterium leprae. These bacteria grow very slowly and it may take up to 20 years to develop signs of the infection.");
        System.out.println("\n   Symptoms may include:");
        System.out.printf("     - Discolored patches of skin");
        System.out.printf("\n     - Growths on the skin");
        System.out.printf("\n     - Thick, stiff or dry skin");
        System.out.printf("\n     - Painless ulcers on the feet soles");
        System.out.printf("\n     - Painless lumps on face or earlobes");
        System.out.printf("\n     - Loss of eyebrows or eyelashes");
        System.out.printf("\n     - Numbness of affected skin areas");
        System.out.printf("\n     - Muscle weakness");
        System.out.printf("\n     - Enlarged nerves"); 
        System.out.printf("\n     - Eye problems"); 
        System.out.printf("\n     - Runny or stuffy nose"); 
        System.out.printf("\n     - Nosebleeds"); 
    }





}