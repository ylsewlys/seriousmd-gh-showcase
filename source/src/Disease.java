import java.util.ArrayList;


public class Disease{

    private String diseaseName;
    private ArrayList<String> symptomsList;


    public Disease(String diseaseName, ArrayList<String> symptomsList){
        this.diseaseName = diseaseName;
        this.symptomsList = symptomsList;
    }


    // SETTERS

    public void setSymptomsList(ArrayList<String> symptomList){
        this.symptomsList = symptomList;
    }




    // GETTERS

    public String getDiseaseName(){
        return this.diseaseName;
    }

    public ArrayList<String> getDiseaseSymptoms(){
        return this.symptomsList;
    }


    
}