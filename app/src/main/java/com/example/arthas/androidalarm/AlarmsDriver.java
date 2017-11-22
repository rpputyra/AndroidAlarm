package com.example.arthas.androidalarm;

/**
 * Created by Rob on 11/21/2017.
 */

        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.time.LocalDate;
        import java.time.LocalTime;
        import java.util.ArrayList;

        import javax.swing.JButton;
        import javax.xml.parsers.DocumentBuilder;
        import javax.xml.parsers.DocumentBuilderFactory;
        import javax.xml.parsers.ParserConfigurationException;
        import javax.xml.transform.Transformer;
        import javax.xml.transform.TransformerException;
        import javax.xml.transform.TransformerFactory;
        import javax.xml.transform.dom.DOMSource;
        import javax.xml.transform.stream.StreamResult;

        import org.w3c.dom.Document;
        import org.w3c.dom.Element;
        import org.w3c.dom.Node;
        import org.w3c.dom.NodeList;
        import org.xml.sax.SAXException;


/**
 * @author Bronwyn Herndon, Rob Putyra, Max Fine
 *
 * Brief Description:
 * In this project, you will write a Java Swing application that will allow a user to add alarms that will go off on certain dates and time.
 * The features that your program must have are as follows:
 *
 */

public class AlarmsDriver implements ActionListener {

    static Alarm tempAlarm; //I haven't fully thought this through but maybe save selected alarm here and use that for the action listener

    private static ArrayList<Alarm> alarmList = new ArrayList<Alarm>();
    static JEntity AlarmWindow;

    private static boolean tick;

    public static void main(String[] args) {

        try {
            File inputFile = new File("file.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("alarm");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                int alarmYear, alarmMonth, alarmDay, alarmHour, alarmMinute, alarmSecond, alarmSnooze;
                String alarmName, alarmMessage;
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    alarmYear = Integer.valueOf(eElement.getElementsByTagName("year").item(0).getTextContent());
                    alarmMonth = Integer.valueOf(eElement.getElementsByTagName("month").item(0).getTextContent());
                    alarmDay = Integer.valueOf(eElement.getElementsByTagName("day").item(0).getTextContent());
                    alarmHour = Integer.valueOf(eElement.getElementsByTagName("hour").item(0).getTextContent());
                    alarmMinute = Integer.valueOf(eElement.getElementsByTagName("minute").item(0).getTextContent());
                    alarmSecond = Integer.valueOf(eElement.getElementsByTagName("second").item(0).getTextContent());
                    alarmName = eElement.getElementsByTagName("name").item(0).getTextContent();
                    alarmMessage = eElement.getElementsByTagName("message").item(0).getTextContent();
                    alarmSnooze = Integer.valueOf(eElement.getElementsByTagName("snooze").item(0).getTextContent());

                    specificAlarm newAlarm = new specificAlarm();
                    try {
                        newAlarm.setAlarm(alarmYear, alarmMonth, alarmDay, alarmHour, alarmMinute, alarmSecond);
                        newAlarm.setAlarmName(alarmName);
                        newAlarm.setMessage(alarmMessage);
                        newAlarm.setNumSnoozes(alarmSnooze);
                        System.out.println(newAlarm.getDateTime());
                        System.out.println(newAlarm.getAlarmTime());
                        alarmList.add(newAlarm);
                    }
                    catch(Exception e) {
                        System.out.println("Invalid alarm from index: " + temp);
                    }

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");

        } catch (ParserConfigurationException e1) {


        } catch (SAXException e1) {

        } catch (IOException e1) {

        }


        //start window
        AlarmWindow = new JEntity();

        //Driver's running while loop. When running becomes false, we save the alarms onto an xml.
        tick = true;
        //While Loop checking current time against next alarm
        while(tick) {

            /**
             *  This sysou print is very important. If there are no alarms in the xml the program will not work without this line.
             *	PLEASE DON'T REMOVE I DON'T KNOW WHY BUT IT WORKS
             */
            System.out.print("");

            if (alarmList.size() > 0) {

                if(alarmList.get(0).timeToRing() ) {

                    NewAlarm pop = new NewAlarm(alarmList.get(0));

                }
            }
        }

        if(tick == false) {

            try {

                int counter = 1;
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                //root elements
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("alarmList");
                doc.appendChild(rootElement);
                for(int i=0; i<alarmList.size();i++) {

                    Alarm XMLAlarm = alarmList.get(i);

                    //alarm elements
                    Element alarm = doc.createElement("alarm");
                    rootElement.appendChild(alarm);

                    //set attribute to alarms element
                    alarm.setAttribute("id", String.valueOf(counter));
                    counter++;

                    //name elements
                    Element name = doc.createElement("name");
                    name.appendChild(doc.createTextNode(XMLAlarm.getAlarmName()));
                    alarm.appendChild(name);

                    //message elements
                    Element message = doc.createElement("message");
                    message.appendChild(doc.createTextNode(XMLAlarm.getMessage()));
                    alarm.appendChild(message);

                    //snooze elements
                    Element snooze = doc.createElement("snooze");
                    snooze.appendChild(doc.createTextNode(String.valueOf(XMLAlarm.getNumSnoozes())));
                    alarm.appendChild(snooze);

                    //date elements
                    Element date = doc.createElement("date");
                    alarm.appendChild(date);

                    //year, month, day elements
                    Element year = doc.createElement("year");
                    year.appendChild(doc.createTextNode(String.valueOf(XMLAlarm.getAlarmYear())));
                    date.appendChild(year);

                    Element month = doc.createElement("month");
                    month.appendChild(doc.createTextNode(String.valueOf(XMLAlarm.getAlarmMonth())));
                    date.appendChild(month);

                    Element day = doc.createElement("day");
                    day.appendChild(doc.createTextNode(String.valueOf(XMLAlarm.getAlarmDay())));
                    date.appendChild(day);

                    //time elements
                    Element time = doc.createElement("time");
                    alarm.appendChild(time);

                    //hour, minute, second
                    Element hour = doc.createElement("hour");
                    hour.appendChild(doc.createTextNode(String.valueOf(XMLAlarm.getAlarmHour())));
                    time.appendChild(hour);

                    Element minute = doc.createElement("minute");
                    minute.appendChild(doc.createTextNode(String.valueOf(XMLAlarm.getAlarmMinute())));
                    time.appendChild(minute);

                    Element second = doc.createElement("second");
                    second.appendChild(doc.createTextNode(String.valueOf(XMLAlarm.getAlarmSecond())));
                    time.appendChild(second);
                }
                //write content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("file.xml"));

                //Output to console for testing
//						StreamResult result = new StreamResult(System.out);

                transformer.transform(source,  result);

                System.out.println("File saved!");

            }
            catch (ParserConfigurationException pce) {
                pce.printStackTrace();
            } catch (TransformerException tfe) {
                tfe.printStackTrace();
            }
        }


    }


    //Action listener is to Control the buttons in the JFrame
    @SuppressWarnings( "unused" )
    @Override
    public void actionPerformed(ActionEvent e) {

        String action = e.getActionCommand();

        String alarmTime;
        String alarmDate;

        switch ( action ) {

            case "NEW ALARM":
                //open window
                NewAlarm popup = new NewAlarm(true);
                break;
            case "DISMISS":
                if (tempAlarm != null) {

                    tempAlarm.dismiss();
                    alarmList.remove(tempAlarm);
                    AlarmWindow.addAlarms();
                }
                break;

            case "DISMISS1":
                if (alarmList.size() > 0) {

                    alarmList.get(0).dismiss();
                    alarmList.remove(0);
                    AlarmWindow.addAlarms();
                    if (NewAlarm.instance != null) {
                        NewAlarm.instance.closeAlarm(true);
                    }
                }
                break;

            case "SNOOZE":

                int snoozes = alarmList.get(0).getNumSnoozes();
                alarmList.get(0).dismiss();

                if (alarmList.get(0).isSpecificALarm ) {

                    specificAlarm a = new specificAlarm();
                    try {
                        a.setAlarm(alarmList.get(0).getAlarmYear(), alarmList.get(0).getAlarmMonth(), alarmList.get(0).getAlarmDay(),
                                alarmList.get(0).getAlarmHour(), alarmList.get(0).getAlarmMinute() + 1, alarmList.get(0).getAlarmSecond() );
                    } catch (Exception e1) {
                        // TODO Decide how to catch
                        System.out.println("Not a valid alarm");
                    }
                    a.setAlarmName( alarmList.get(0).getAlarmName() );
                    a.setMessage(alarmList.get(0).getMessage());
                    a.setNumSnoozes( ++snoozes );
                    alarmList.set(0, a);
                }
                else {
                    Timer a = new Timer();

                    try {
                        a.setTimer(0, 0, 0, 0, 1, 0 );
                    } catch (Exception e1) {

                        System.out.println("Not a valid timer");
                    }

                    a.setAlarmName( alarmList.get(0).getAlarmName() );
                    a.setMessage(alarmList.get(0).getMessage());
                    a.setNumSnoozes( ++snoozes );
                    alarmList.set(0, a);
                }

                NewAlarm.instance.closeAlarm(true);
                AlarmWindow.addAlarms();
                break;

            case "Okay":
                //Closes New Alarm pop up window.
                //it will establish if it's closing a timer or alarm window then create a new object of that type and add it to the array list
                if ( NewAlarm.instance.isAlarmType() ) {

                    specificAlarm na = new specificAlarm();
                    generateAlarm(na, NewAlarm.instance.getDate(), NewAlarm.instance.getTime(), NewAlarm.instance.getAlarmName(), NewAlarm.instance.getAlarmMessage() );
                }
                else{
                    Timer nt = new Timer();
                    generateTimer(nt, NewAlarm.instance.getHours(), NewAlarm.instance.getMinutes(), NewAlarm.instance.getSeconds(), NewAlarm.instance.getAlarmName(), NewAlarm.instance.getAlarmMessage() );
                }

                NewAlarm.instance.closeAlarm(false);
                break;

            case "NEW TIMER":
                NewAlarm timer = new NewAlarm(false);

                break;
            case "selectedAlarm":

                //find the Alarm in the Arraylist that the button is referring to. Then set the Temp Alarm to that alarm for further Use.
                //In the future we may possibly want to remove it form the Arraylist when we add it to temp Alarm.
                if ( e.getSource().getClass() == JButton.class ) {

                    JButton b = ( JButton ) e.getSource();

                    String name = b.getText().toString().substring(0,  b.getText().toString().indexOf("Date:"));
                    for ( int i = 0; i < alarmList.size(); i++ ) {

                        if ( alarmList.get( i ).alarmName.trim().equals( name.trim() ) ) {
                            tempAlarm = alarmList.get( i );
                        }

                    }
                }

                break;
        }
    }


    //Rob I need help with this, I'm not sure what the input into timer needs to be
    private void generateTimer(Timer t, String _hour, String _minute, String _second, String name, String message) {
        int hours = 0, minutes = 0, seconds = 0;
        try {

            hours = Integer.parseInt(_hour);
            minutes = Integer.parseInt( _minute );
            seconds = Integer.parseInt( _second );


        } catch ( Exception e ) {

            System.out.println("I don't suspect this is a real number");
        }
        try {
            t.setAlarmName( name );
            t.setMessage(message);
            t.setTimer(0, 0, 0, hours, minutes, seconds);
            alarmList.add(t);
            AlarmWindow.addAlarms();


        } catch (Exception e) {
            System.out.println("That is not a Valid Timer");
        }


    }

    private void generateAlarm(specificAlarm a, String date, String time, String name, String message){

        int year=0, month=0, day=0, hour=0, minute=0, second=0;

        try {
            year = Integer.parseInt( date.substring(6) );
            month = Integer.parseInt( date.substring(0,2) );
            day = Integer.parseInt( date.substring(3,5) );

            hour = Integer.parseInt( time.substring(0,2) );
            minute = Integer.parseInt( time.substring(3, 5) );
            second = Integer.parseInt( time.substring(6) );


        } catch (Exception e) {
            System.out.println( "I don't suspect those are real numbers" );
        }
        try {

            a.setAlarm(year, month, day, hour, minute, second);
            a.setMessage(message);
            a.setAlarmName( name );
            alarmList.add(a);

        } catch (Exception e) {
            System.out.println("The Alarm Values are invalid");
            e.printStackTrace();
        }

        AlarmWindow.addAlarms();
    }

    //Sort the alarm by comparing elements at index i to the next index, and replace the alarmList at
    //index if an appropriate element is smaller than the next index element.
    //We may get an IndexOutOfBounds exception here, most likely from "alarmList.get(i + 1)".
    public static void sortAlarmList(){
        if(alarmList.size() <= 1 ) {
            return;
        }
        boolean isSorted = false;
        boolean isUpToDate = false;
        while(isUpToDate == false) {
            isUpToDate = true;
            for(int i = 0; i<alarmList.size(); i++) {
                if(alarmList.get(i).validTimer() == false) {
                    alarmList.remove(i);
                    isUpToDate = false;
                    break;
                }
            }
        }

        while(isSorted == false){
            for (int i = 0; i < alarmList.size()-1; i++){
                isSorted=true;
                //test objects

                LocalDate LocalDate0 = LocalDate.of(alarmList.get(i).getAlarmYear(),
                        alarmList.get(i).getAlarmMonth(), alarmList.get(i).getAlarmDay());
                LocalDate LocalDate1 = LocalDate.of(alarmList.get(i+1).getAlarmYear(),
                        alarmList.get(i+1).getAlarmMonth(), alarmList.get(i+1).getAlarmDay());

                LocalTime LocalTime0 = LocalTime.of(alarmList.get(i).getAlarmHour(),
                        alarmList.get(i).getAlarmMinute(), alarmList.get(i).getAlarmSecond());
                LocalTime LocalTime1 = LocalTime.of(alarmList.get(i+1).getAlarmHour(),
                        alarmList.get(i+1).getAlarmMinute(), alarmList.get(i+1).getAlarmSecond());

                Alarm tAlarm1 = alarmList.get(i);
                Alarm tAlarm2 = alarmList.get(i+1);



                if ((LocalDate0.compareTo(LocalDate1)) == 1) {
                    Alarm tempAlarm = alarmList.get(i);
                    alarmList.set(i, alarmList.get(i+1));
                    alarmList.set(i+1, tempAlarm);
                    isSorted = false;
                    break;
                }
                else if((LocalDate0.compareTo(LocalDate1)) == 0) {
                    if((LocalTime0.compareTo(LocalTime1)) == 1) {
                        Alarm tempAlarm = alarmList.get(i);
                        alarmList.set(i, alarmList.get(i+1));
                        alarmList.set(i+1, tempAlarm);
                        isSorted = false;
                        break;
                    }
                    else {
                    }
                }
                else {
                }
            }
        }
    }

    /**
     * @return the alarmList
     */
    public static ArrayList<Alarm> getAlarmList() {
        return alarmList;
    }


    public static void setTick(boolean b) {
        //set tick to false to exit while loop
        tick = b;

    }

}
