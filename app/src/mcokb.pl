% MCO Knowledge Base


% a chief complaint is a concise statement in English or other natural language of the symptoms that caused a patient to seek medical care
% ask for chief complaint and decide which diseases to consider



:- dynamic(has_symptom/1).
:- dynamic(yes/1).
:- dynamic(asked/1).

:- dynamic(disease/1).
:- dynamic(emergency/1).
:- dynamic(include/1).
:- dynamic(possible/1).

:- dynamic(diagnose/2).

:- dynamic(dengue/1).
:- dynamic(hypertension/1).
:- dynamic(hepatitis_b/1).
:- dynamic(cholera/1).
:- dynamic(influenza/1).
:- dynamic(measles/1).
:- dynamic(tuberculosis/1).
:- dynamic(typhoid/1).
:- dynamic(pneumonia/1).
:- dynamic(leprosy/1).

sum(X, Z) :-
    Z is X + 10.



test(Weight) :-
    sum(0, A),
    sum(10, B),
    sum(20, C),
    Weight is (A + B + C).


has_symptom(Symptom, Weight, Result) :-
    ((yes(Symptom) -> Result is Weight) ; Result is 0).


/* Call emergency predicate. Can be added based on the degree of some diseases.  */

call_emergency :- 
    emergency(hypertensive_crisis).




emergency(hypertensive_crisis) :-
    yes(too_high_blood_pressure),
    (yes(severe_headaches);
    yes(chest_pain);
    yes(dizziness);
    yes(difficulty_breathing);
    yes(nausea);
    yes(vomiting);
    yes(vision_changes);
    yes(anxiety);
    yes(confusion);
    yes(buzzing_in_the_ears);
    yes(nosebleeds);
    yes(abnormal_heart_rhythm)).



/* Existing Symptoms List (For Testing)*/



/* List of diseases the system can include based on the chief complaint. We can find the list by calling the function 'include(X).' in Prolog */

include(dengue) :- 
    yes(high_fever);
    yes(severe_headaches);
    yes(pain_behind_eyes);
    yes(muscle_and_joint_pains);
    yes(nausea);
    yes(vomiting);
    yes(swollen_glands);
    yes(rash).


include(hypertension) :-
    yes(high_blood) ; yes(too_high_blood_pressure).


include(hepatitis_b) :-
    yes(fever);
    yes(jaundice);
    yes(fatigue);
    yes(loss_of_appetite);
    yes(nausea);
    yes(vomiting);
    yes(abdominal_pain);
    yes(dark_urine);
    yes(clay_colored_bowel_movements);
    yes(joint_pain).


include(influenza) :-
    (yes(fever) ; yes(feverish_or_chills));
    yes(cough);
    yes(sore_throat);
    yes(runny_or_stuffy_nose);
    yes(muscle_or_body_aches);
    yes(headaches);
    yes(fatigue);
    (yes(vomiting) ; yes(diarrhea)).


include(cholera) :-
    yes(vomiting);
    yes(rapid_dehydration);
    yes(rapid_heart_rate);
    yes(low_blood_pressure);
    yes(loss_of_skin_elasticity);
    yes(muscle_cramps);
    yes(thirst);
    yes(fatigue);
    yes(abdominal_pain);
    yes(frequent_painless_watery_stools).



include(measles) :- 
    yes(high_fever);
    yes(cough);
    yes(runny_or_stuffy_nose);
    yes(red_watery_eyes);
    yes(koplik_spots_in_the_mouth);
    yes(measles_rash).

include(tuberculosis) :- 
    yes(weight_loss);
    yes(fever);
    yes(night_sweats);
    yes(cough);
    yes(chest_pain);
    yes(coughing_blood).


include(typhoid) :- 
    yes(fever);
    yes(headache);
    yes(abdominal_pain);
    yes(diarrhea);
    yes(nausea);
    yes(loss_of_appetite);
    yes(weakness);
    yes(rash);
    yes(joint_pain).
    
include(pneumonia) :-
    yes(cough);
    yes(fever);
    yes(shortness_of_breath);
    yes(chest_pain);
    yes(rapid_heartbeat);
    yes(loss_of_appetite);
    yes(weakness);
    yes(muscle_or_body_aches);
    yes(joint_pain).


include(leprosy) :-
    yes(discolored_patches_of_skin);
    yes(growths_on_the_skin);
    yes(stiff_or_dry_skin);
    yes(painless_ulcers_on_feet_soles);
    yes(painless_lumps_on_face_or_earlobes);
    yes(loss_of_eyebrows_or_eyelashes);
    yes(numbness_of_affected_skin_areas);
    yes(muscle_weakness);
    yes(enlarged_nerves);
    yes(eye_problems);
    yes(runny_or_stuffy_nose);
    yes(nosebleeds).




/* Weight computations and truth value determination */

dengue(X) :- 
    has_symptom(high_fever, 0.9, X1),
    has_symptom(severe_headaches, 0.6, X2),
    has_symptom(pain_behind_eyes, 0.6, X3),
    has_symptom(muscle_and_joint_pains, 0.9, X4),
    has_symptom(nausea, 0.6, X5),
    has_symptom(vomiting, 0.6, X6),
    has_symptom(swollen_glands, 0.4, X7),
    has_symptom(rash, 0.5, X8),

    Weight is (X1 + X2 + X3 + X4 + X5 + X6 + X7 + X8),
    X is Weight,
    (Weight > 1) -> true.



sampledisease(X) :- 
    has_symptom(symptom_one, 0.9, X1),
    has_symptom(symptom_two, 0.7, X2),
    has_symptom(symptom_three, 0.7, X3),
    has_symptom(symptom_four, 0.5, X4),
    has_symptom(symptom_five, 0.4, X5),
    has_symptom(symptom_six, 0.4, X6),
    has_symptom(symptom_seven, 0.2, X7),
    has_symptom(symptom_eight, 0.1, X8),

    Weight is (X1 + X2 + X3 + X4 + X5 + X6 + X7 + X8),
    X is Weight,
    (Weight > 1) -> true.


hypertension(X) :-
    has_symptom(high_blood, 1.1, X1),
    has_symptom(too_high_blood_pressure, 1.5, X2),
    Weight is (X1 + X2),
    X is Weight,
    (Weight > 1) -> true.



hepatitis_b(X) :- 
    has_symptom(fever, 0.8, X1),
    has_symptom(jaundice, 0.9, X2),
    has_symptom(fatigue, 0.7, X3),
    has_symptom(loss_of_appetite, 0.8, X4),
    has_symptom(nausea, 0.6, X5),
    has_symptom(vomiting, 0.5, X6),
    has_symptom(abdominal_pain, 0.7, X7),
    has_symptom(dark_urine, 0.6, X8),
    has_symptom(clay_colored_bowel_movements, 0.4, X9),
    has_symptom(joint_pain, 0.3, X10),

    Weight is (X1 + X2 + X3 + X4 + X5 + X6 + X7 + X8 + X9 + X10),
    X is Weight,
    (Weight > 1) -> true.


influenza(X) :-
    has_symptom(fever, 0.8, X1),
    has_symptom(feverish_or_chills, 0.7, X2),
    has_symptom(cough, 0.9, X3),
    has_symptom(sore_throat, 0.6, X4),
    has_symptom(runny_or_stuffy_nose, 0.7, X5),
    has_symptom(muscle_or_body_aches, 0.8, X6),
    has_symptom(headaches, 0.7, X7),
    has_symptom(fatigue, 0.8, X8),
    has_symptom(vomiting, 0.4, X9),
    has_symptom(diarrhea, 0.3, X10),

    Weight is (X1 + X2 + X3 + X4 + X5 + X6 + X7 + X8 + X9 + X10),
    X is Weight,
    (Weight > 1) -> true.



cholera(X) :-
    has_symptom(vomiting, 0.9, X1),
    has_symptom(rapid_dehydration, 0.9, X2),
    has_symptom(rapid_heart_rate, 0.6 , X3),
    has_symptom(low_blood_pressure, 0.5, X4),
    has_symptom(loss_of_skin_elasticity, 0.7, X5),
    has_symptom(muscle_cramps, 0.8, X6),
    has_symptom(thirst, 0.6, X7),
    has_symptom(fatigue, 0.5, X8),
    has_symptom(abdominal_pain, 0.6, X9),  
    has_symptom(frequent_painless_watery_stools, 0.9, X10),  

    Weight is (X1 + X2 + X3 + X4 + X5 + X6 + X7 + X8 + X9 + X10),
    X is Weight,
    (Weight > 1) -> true.   



measles(X) :-
    has_symptom(high_fever, 0.8, X1),
    has_symptom(cough, 0.5, X2),
    has_symptom(runny_or_stuffy_nose, 0.7, X3),
    has_symptom(red_watery_eyes, 0.6, X4),
    has_symptom(koplik_spots_in_the_mouth, 0.4, X5),
    has_symptom(measles_rash, 0.9, X6),

    Weight is (X1 + X2 + X3 + X4 + X5 + X6),
    X is Weight,
    (Weight > 1) -> true.

tuberculosis(X) :-
    has_symptom(weight_loss, 0.5 , X1),
    has_symptom(fever, 0.8 , X2),
    has_symptom(night_sweats, 0.5 , X3),
    has_symptom(cough, 0.9 , X4),
    has_symptom(chest_pain, 0.2, X5),
    has_symptom(coughing_blood, 0.2, X6),

    Weight is (X1 + X2 + X3 + X4 + X5 + X6),
    X is Weight,
    (Weight > 1) -> true.
    
typhoid(X) :-
    has_symptom(fever, 0.9 , X1),
    has_symptom(headaches, 0.7 , X2),
    has_symptom(abdominal_pain, 0.8 , X3),
    has_symptom(diarrhea, 0.6 , X4),
    has_symptom(nausea,  0.7, X5),
    has_symptom(loss_of_appetite, 0.8 , X6),
    has_symptom(weakness, 0.6 , X7),
    has_symptom(rash, 0.1 , X8),
    has_symptom(joint_pain, 0.2 , X9),
    
    Weight is (X1 + X2 + X3 + X4 + X5 + X6 + X7 + X8 + X9),
    X is Weight,
    (Weight > 1) -> true.
    
    
pneumonia(X) :-
    has_symptom(fever, 0.8 , X1),
    has_symptom(shortness_of_breath, 0.9 , X2),
    has_symptom(chest_pain, 0.6 , X3),
    has_symptom(rapid_heartbeat, 0.4 , X4),
    has_symptom(loss_of_appetite, 0.6 , X5),
    has_symptom(weakness, 0.6 , X6),
    has_symptom(muscle_or_body_aches, 0.5 , X7),
    has_symptom(joint_pain, 0.2 , X8),
    has_symptom(cough, 0.9 , X9),
    
    Weight is (X1 + X2 + X3 + X4 + X5 + X6 + X7 + X8 + X9),
    X is Weight,
    (Weight > 1) -> true.


leprosy(X) :- 
    has_symptom(discolored_patches_of_skin, 0.8, X1),
    has_symptom(growths_on_the_skin, 0.7, X2),
    has_symptom(stiff_or_dry_skin, 0.6, X3),
    has_symptom(painless_ulcers_on_feet_soles, 0.4, X4),
    has_symptom(painless_lumps_on_face_or_earlobes, 0.6, X5),
    has_symptom(loss_of_eyebrows_or_eyelashes, 0.5, X6),
    has_symptom(numbness_of_affected_skin_areas, 0.6, X7),
    has_symptom(muscle_weakness, 0.4, X8),
    has_symptom(enlarged_nerves, 0.7, X9),
    has_symptom(eye_problems, 0.2, X10),
    has_symptom(runny_or_stuffy_nose, 0.7, X11),
    has_symptom(nosebleeds, 0.5, X12),

    Weight is (X1 + X2 + X3 + X4 + X5 + X6 + X7 + X8 + X9 + X10 + X11 + X12),
    X is Weight,
    (Weight > 1) -> true.







/* 
    Start of diagnosis. Calling 'diagnose(X, Score).' in Prolog would give the list of possible diseases and their respective weights for possible diagnoses. 
    If a specific disease's weight does not exceed its threshold value, it will not be listed in the 'diagnose(X, Score).' predicate's result
    
    Example if high fever and severe headaches are the prevalent symptoms in the kb: 
        ?- diagnose(X, Score).
        Output:
        X = dengue,
        Score = 1.3 ;
        X = measles,
        Score = 2.
*/
diagnose(dengue, Score) :- possible(dengue), dengue(Score).
diagnose(hypertension, Score) :- possible(hypertension), hypertension(Score).
diagnose(hepatitis_b, Score) :- possible(hepatitis_b), hepatitis_b(Score).
diagnose(cholera, Score) :- possible(cholera), cholera(Score).
diagnose(influenza, Score) :- possible(influenza), influenza(Score).
diagnose(measles, Score) :- possible(measles), measles(Score).
diagnose(tuberculosis, Score) :- possible(tuberculosis), tuberculosis(Score).
diagnose(typhoid, Score) :- possible(typhoid), typhoid(Score).
diagnose(pneumonia, Score) :- possible(pneumonia), pneumonia(Score).
diagnose(leprosy, Score) :- possible(leprosy), leprosy(Score).

/* For reinitialization of medical expert system*/
undo :-
    retractall(yes(_)),
    retractall(include(_)),
    retractall(emergency(_)),
    retractall(asked(_)),
    retract(dengue(_)),
    retract(hypertension(_)),
    retract(hepatitis_b(_)),
    retract(cholera(_)),
    retract(influenza(_)),
    retract(measles(_)),
    retract(tuberculosis(_)),
    retract(typhoid(_)),
    retract(pneumonia(_)),
    retract(leprosy(_)),
    retractall(diagnose(_, _)).
