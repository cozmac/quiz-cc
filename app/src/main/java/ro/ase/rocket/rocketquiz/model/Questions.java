package ro.ase.rocket.rocketquiz.model;


public class Questions {

    public String mQuestions[] =
            {
                    "What temperature does water boil at?",
                    "Where is the smallest bone in the body?",
                    "When did the Second World War end?",
                    "Who said E=m*cˆ2?",
                    "Who painted the Mona Lisa?",
                    "What’s the first name of Shakespeare?",
                    "Poker: AAA68",
                    "What is the capital of Turkey?",
                    "How many continents are there?",
                    "What is a very cold part of Russia?",
                    "Which river is flowing through Rome?",
                    "In which country is Krakow located?",
                    "What is the second largest country in Europe after Russia?",
                    "On which continent can you visit Sierra Leone?",
                    "Which Turkish city has the name of a cartoon character?",
                    "How many time zones are there in the world?",
                    "Who is the dictator of Cuba?",
                    "Who was the dictator of Iraq?",
                    "In which state was George W Bush governor before becoming President of the United States?",
                    "In which state was Bill Clinton governor before becoming President of the United States?",
                    "In what year became John Major the leader of the Conservative Party?",
                    "In what year was the Berlin wall built?",
                    "How were called the British women who fought for women s suffrage?",
                    "Who was the Portuguese dictator at the time of Franco?",
                    "What is the defoliant called that was used in Vietnam?",
                    "What is the most spoken language in Belgium?",
                    "How many heads of presidents are carved into Mount Rushmore?",
                    "For how many years is a French president elected?",
                    "Who is the only British Prime Minister to be murdered?",
                    "In which country happened the Orange Revolution between 2004-2005?",
                    "Which country had a Prime Minister and President who were twin brothers?",
                    "In 2009 Indian Premier League who got the title of Player of the series?",
                    "Which team won the IPL (2009)?",
                    "Which player scored most runs in IPL (2009)?",
                    "Which company replaced DLF as the new title sponsor in 2013 IPL?",
                    "In which year the Olympic Games were first televised ?",
                    "What does the five interlinked Olympic rings on the flag represent?",
                    "Which country has the honour of been the first in the Parade of Nations during opening ceremony of Olympics?",
                    "Olympic Flag was introduced for first time during which Olympic Games ?",
                    "What was the name of the mascot for the 1992 Olympic games?",
                    "When was the first time Olympic Flame was relayed to the host city to light the Olympic Cauldron for which games?",
                    "In which year were the first Winter Olympics held?",
                    "Where was the opening ceremony of IPL (2013) held on 2 April 2013?",
                    "Which International star performed in the opening ceremony of 2013 IPL?",
                    "Which team won the 2014 Indian Premier League tournament?"

            };

    private String mChoices[][] = {
            {"100 C","96 C"," 108 C","104 C"},
            {"Foot","Hand","Ear","Finger"},
            {"1966","1945","1928","1948"},
            {"Benjamin Franklin","Tesla","Edison","Einstein"},
            {"Dali","Da Vinci","Van Gogh","Picasso"},
            {"William","John","Bill","Daniel"},
            {"Straight","Two pair","Three of a kind","Full house"},
            {"Beirut", "Paris", "Ankara","Rome"},
            {"Seven", "Eight", "Four","Five"},
            {"Kazan", "Tomsk", "Yemen","Siberia"},
            {"Florence", "Venice", "Tiber","Eufrat"},
            {"Scotland", "Poland", "Spain","Germany"},
            {"Germany", "France", "Turkey","Sweden"},
            {"Africa", "Europe", "America","Australia"},
            {"Batman", "Ankara", "Istanbul","Bodrum"},
            {"Twenty-four", "Twenty", "Ten","Eleven"},
            {"Fidel Castro", "Mario Sarri", "Frank Jhon","Conte"},
            {"Bin Laden", "Saddam Hussain", "Obama","Don Rossi"},
            {"Miami", "Boston", "California","Texas"},
            {"Ohio", "Arkansas", "Nevada","Arizona"},
            {"In the year 1995", "In the year 1990", "In the year 1998","In the year 1994"},
            {"In the year 1963", "In the year 1961", "In the year 1969","In the year 1964"},
            {"Instructors", "Sufragettes", "Architects","Clerks"},
            {"Smeagol", "Salazar", "Malzahar","Bulbazar"},
            {"Agent Orange", "Agent Green", "Agent Yellow","Agent Red"},
            {"English language", "Dutch language", "Adangbe language","Armenian language"},
            {"Six heads", "Five heads", "Four heads","Two heads"},
            {"Twenty-four years", "Seven years", "Ten years","Eleven years"},
            {"David Cameron", "Spencer Percival", "Boris Johnson","Theresa May"},
            {"France", "Italy", "Spain","Ukraine"},
            {"Greece", "Norway", "Poland","Sweden"},
            {"Adam Gilchrist", "Sachin Tendulkar", "Robin Uthappa","Shane Watson"},
            {"Deccan Chargers", "Chennai Super Kings", "Kolkata Knight Riders","Delhi Daredevils"},
            {"Sanath Jaysuriya", "Sachin Tendulkar", "Matthew Hayden","Virender Sehwag"},
            {"Patanjali", "Nokia", "Hindustan Unilever","PepsiCo"},
            {"1952, Helsinki", "1956, Melbourne", "1948, London","1936, Berlin"},
            {"Unity", "Color rings", "None of the above","Continents"},
            {"Host Country", "Greece", "Country who has maximum golds in pervious year","Countries come out in alphabetical order"},
            {"1932, Lake Placid", "1920,Antwerp", "1960,Squaw Valley","1948, St. Moritz"},
            {"Bear", "Elk", "Beaver","Cobi"},
            {"1936,Berlin", "1996,Atlanta", "1988,Seoul","1980, Moscow"},
            {"1924", "1932", "1938","1944"},
            {"Rajiv Gandhi International Cricket Stadium, Hyderabad", "Salt Lake Stadium in Kolkata", "Green Park, Kanpur","M. Chinnaswamy Stadium, Bangalore"},
            {"Jennifer Lopez", "Akon", "Pitbull","None of the above"},
            {"Kolkata Knight Riders", "Chennai Super Kings", "Deccan Chargers","Royal Challengers Bangalore"}

    };


    private String mCorrectAnswers[]={"100 C","Ear","1945","Einstein","Da Vinci","William","Three of a kind", "Ankara", "Seven", "Siberia", "Tiber", "Poland", "France", "Africa", "Batman", "Twenty-four",
            "Fidel Castro","Saddam Hussain","Texas","Arkansas","In the year 1990","In the year 1961","Sufragettes","Salazar",
            "Agent Orange","Dutch language","Four heads","Seven years","Spencer Percival","Ukraine","Poland"
            ,"Adam Gilchrist","Deccan Chargers","Sachin Tendulkar","PepsiCo","1936, Berlin","Continents","Greece","1920,Antwerp",
            "Cobi","1996,Atlanta","1924","Salt Lake Stadium in Kolkata","Pitbull","Kolkata Knight Riders"};

    public String getQuestion(int a){
        String question= mQuestions[a];
        return question;
    }


    public String getChoice1(int a){
        String choice= mChoices[a][0];
        return choice;
    }

    public String getChoice2(int a){
        String choice= mChoices[a][1];
        return choice;
    }
    public String getChoice3(int a){
        String choice= mChoices[a][2];
        return choice;
    }
    public String getChoice4(int a){
        String choice= mChoices[a][3];
        return choice;
    }

    public String getCorrectAnswer(int a){
        String answer=mCorrectAnswers[a];
        return answer;
    }



}