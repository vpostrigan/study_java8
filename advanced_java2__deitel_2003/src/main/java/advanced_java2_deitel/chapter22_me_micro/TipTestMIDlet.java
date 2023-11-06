package advanced_java2_deitel.chapter22_me_micro;

// J2ME Java package subset

import java.io.*;

// J2ME packages
//import javax.microedition.midlet.*;
//import javax.microedition.lcdui.*;
//import javax.microedition.io.*;

// TipTestMIDlet.java
// Receives TipTest from Servlet
public class TipTestMIDlet /*extends MIDlet*/ {
/*
    private Display display; // display manager

    // Screens displayed to user
    private List mainScreen;
    private List welcomeScreen;
    private Form infoScreen;
    private Form tipScreen;
    private Form answerScreen;

    // actions for soft-buttons
    private Command selectCommand;
    private Command nextCommand;
    private Command backCommand;

    private static final String servletBaseURL = "http://localhost:8080/advjhtp1/";
    private static final String welcomeServletName = "welcome";
    private static final String welcomeServletURL = servletBaseURL + welcomeServletName;

    // welcome servlet determines tip test servlet name
    private String tipTestServletName;

    private String sessionID;

    // constructor initializes display and main Screen
    public TipTestMIDlet() {
        // create soft button commands
        selectCommand = new Command("Select", Command.OK, 0);
        nextCommand = new Command("Next Tip", Command.OK, 0);
        backCommand = new Command("Back", Command.BACK, 1);

        // create main screen allowing welcome servlet connection
        mainScreen = new List("TipTestMIDlet", List.IMPLICIT);
        mainScreen.addCommand(selectCommand);

        // allow soft button access for mainScreen
        mainScreen.setCommandListener(
                new CommandListener() {
                    // invoked when user presses soft button
                    public void commandAction(Command command, Displayable displayable) {
                        // get data from welcome servlet
                        String data = getServerData(welcomeServletURL);

                        // create welcome Screen using servlet data
                        display.setCurrent(createWelcomeScreen(data));
                    }
                }
        );

        // get appropriate Display for device
        display = Display.getDisplay(this);
    }

    // start MIDlet
    public void startApp() {
        // set display to main Screen
        display.setCurrent(mainScreen);
    }

    // pause MIDlet
    public void pauseApp() {
    }

    // destroy MIDlet
    public void destroyApp(boolean unconditional) {
    }

    // create "welcome" Screen introducing tip test
    private Screen createWelcomeScreen(String data) {
        String list[] = parseData(data, ';');

        // create Screen welcoming user to tip test
        welcomeScreen = new List(list[0], List.IMPLICIT);

        welcomeScreen.append("Take TipTest", null);
        welcomeScreen.addCommand(selectCommand);
        welcomeScreen.addCommand(backCommand);

        // get URL of information page
        final String url = new String(list[1].toCharArray());

        // allow soft button access for welcomeScreen
        welcomeScreen.setCommandListener(
                new CommandListener() {
                    // invoked when user presses soft button
                    public void commandAction(Command command, Displayable displayable) {
                        // soft button pressed is SELECT button
                        if (command.getCommandType() == Command.OK) {

                            // get data from static page
                            String data = getServerData(servletBaseURL + url);

                            // display this data
                            display.setCurrent(createInformationScreen(data));
                        }
                        // soft button pressed is BACK button
                        else if (command.getCommandType() == Command.BACK) {
                            display.setCurrent(mainScreen);
                        }
                    }
                }
        );
        return welcomeScreen;
    }

    // create Screen showing servlets to which client can connect
    private Screen createInformationScreen(String data) {
        String list[] = parseData(data, ';');

        // create Form showing available servlets
        infoScreen = new Form("Information");

        // create StringItem that provides directions
        StringItem infoTitle = new StringItem(list[0], null);
        infoScreen.append(infoTitle);

        // create ChoiceGroup allowing user to select servlet
        final ChoiceGroup choices = new ChoiceGroup("", ChoiceGroup.EXCLUSIVE);
        choices.append(list[1], null);

        // append ChoiceGroup to Form
        infoScreen.append(choices);

        infoScreen.addCommand(selectCommand);
        infoScreen.addCommand(backCommand);

        // allow soft button access for this Screen
        infoScreen.setCommandListener(
                new CommandListener() {
                    // invoked when user presses soft button
                    public void commandAction(Command command, Displayable displayable) {
                        // soft button pressed is SELECT button
                        if (command.getCommandType() == Command.OK) {

                            // user chooses which servlet to connect
                            int selectedIndex = choices.getSelectedIndex();

                            tipTestServletName = choices.getString(selectedIndex);

                            // connect to servlet and receive data
                            String data = getServerData(servletBaseURL + tipTestServletName);

                            // display next Screen according to data
                            display.setCurrent(createTipTestScreen(servletBaseURL + data));
                        }
                        // soft button pressed is BACK button
                        else if (command.getCommandType() == Command.BACK)
                            display.setCurrent(welcomeScreen);
                    }
                }
        );

        return infoScreen;
    }

    // create Screen to display Tip Test
    private Screen createTipTestScreen(String data) {
        // parse server data
        String list[] = parseData(data, '\n');

        // create new Form to display test
        tipScreen = new Form("Tip Test");

        // create image from server data
        Image serverImage = getServerImage(list[0]);

        // append image to Form
        if (serverImage != null)
            tipScreen.append(serverImage);

        String choiceList[] = new String[4];

        // construct list for ChoiceGroup from data
        for (int i = 0; i < choiceList.length; i++)
            choiceList[i] = list[i + 1];

        // create ChoiceGroup allowing user to input choice
        final ChoiceGroup choices = new ChoiceGroup("Tip Test",
                ChoiceGroup.EXCLUSIVE, choiceList, null);

        // append ChoiceGroup to Form
        tipScreen.append(choices);

        tipScreen.addCommand(selectCommand);

        // allow soft button access for this Screen
        tipScreen.setCommandListener(
                new CommandListener() {
                    // invoked when user presses soft button
                    public void commandAction(Command command, Displayable displayable) {
                        // send user selection to servlet
                        int selection = choices.getSelectedIndex();

                        String result = postData(selection);

                        // display results
                        display.setCurrent(createAnswerScreen(result));
                    }
                }
        );

        return tipScreen;
    }

    // create Screen to display Tip Test answer and results
    private Screen createAnswerScreen(String data) {
        // parse server data
        String list[] = parseData(data, '\n');

        // create new Form to display test answers
        answerScreen = new Form(list[0]);

        // create StringItem showing tip name
        StringItem tipNameItem = new StringItem("Tip Name:\n", list[1]);

        // create StringItem showing tip description
        StringItem tipDescriptionItem = new StringItem("\nTip Description:\n", list[2]);

        // append StringItems to Form
        answerScreen.append(tipNameItem);
        answerScreen.append(tipDescriptionItem);

        answerScreen.addCommand(nextCommand);

        // allow soft button access for this Screen
        answerScreen.setCommandListener(
                new CommandListener() {
                    // invoked when user presses soft button
                    public void commandAction(Command command, Displayable displayable) {
                        // get next question
                        String data = getServerData(servletBaseURL + tipTestServletName);

                        // display next question
                        display.setCurrent(createTipTestScreen(servletBaseURL + data));
                    }
                }
        );

        return answerScreen;
    }

    // sends user's test selection to servlet
    private String postData(int selection) {
        // connect to server, then post data
        try {
            // connect to server sending User-Agent header
            HttpConnection httpConnection =
                    (HttpConnection) Connector.open(servletBaseURL + tipTestServletName, Connector.READ_WRITE);

            setUserAgentHeader(httpConnection);

            // send sessionID, if one exists
            if (sessionID != null)
                httpConnection.setRequestProperty("cookie", sessionID);

            // inform servlet of post request
            httpConnection.setRequestMethod(HttpConnection.POST);

            // open output stream to servlet
            DataOutputStream out = httpConnection.openDataOutputStream();

            // send selection
            out.writeUTF(Integer.toString(selection));
            out.flush();

            // get result from servlet
            String data = getData(httpConnection);

            httpConnection.close(); // close connection

            return data;
        } catch (IOException ioException) {
            // handle if MIDlet cannot open HTTP connection
            ioException.printStackTrace();
        }

        return null;
    }

    // string tokenizer parses String into sub-string array
    private String[] parseData(String data, char delimiter) {
        int newLines = 0;

        // determine number of delimiter characters in String
        for (int i = 0; i < data.length(); i++)
            // increase number of delimiters by one
            if (data.charAt(i) == delimiter)
                newLines++;

        // create new String array
        String list[] = new String[newLines];

        int oldNewLineIndex = 0;
        int currentNewLineIndex;

        // store Strings into array based on demiliter
        for (int i = 0; i < newLines; i++) {

            // determine index where delimiter occurs
            currentNewLineIndex = data.indexOf(delimiter, oldNewLineIndex);

            // extract String within delimiter characters
            list[i] = data.substring(oldNewLineIndex, currentNewLineIndex - 1);

            oldNewLineIndex = currentNewLineIndex + 1;
        }

        return list;
    }

    // connect to server and receive data
    private String getServerData(String serverUrl) {
        // connect to server, then get data
        try {
            // connect to server sending User-Agent header
            HttpConnection httpConnection = (HttpConnection) Connector.open(serverUrl);
            setUserAgentHeader(httpConnection);

            // send sessionID to server
            if (sessionID != null)
                httpConnection.setRequestProperty("cookie", sessionID);

            // get sessionID from server
            String sessionIDHeaderField = httpConnection.getHeaderField("Set-cookie");

            // store sessionID from cookie
            if (sessionIDHeaderField != null) {
                int index = sessionIDHeaderField.indexOf(';');
                sessionID = sessionIDHeaderField.substring(0, index);
            }

            // receive server data
            String data = getData(httpConnection);

            httpConnection.close(); // close connection

            return data;
        } catch (IOException e) {
            // handle exception communicating with HTTP server
            e.printStackTrace();
        }
        return null;
    }

    // downloads an image from a server
    private Image getServerImage(String imageUrl) {
        // download image
        try {
            // open connection to server
            HttpConnection httpConnection = (HttpConnection) Connector.open(imageUrl);

            int connectionSize = (int) httpConnection.getLength();
            byte imageBytes[] = new byte[connectionSize];

            // read image from server
            InputStream input = httpConnection.openInputStream();
            input.read(imageBytes);

            // create image from imageBytes
            return Image.createImage(imageBytes, 0, connectionSize);
        } catch (IOException e) { // handle exception if InputStream cannot input bytes
            e.printStackTrace();
        }
        return null;
    }

    // set User-Agent header to identify client to servlet
    private void setUserAgentHeader(HttpConnection httpConnection) {
        // set User-Agent header
        try {
            // use profile/configuration properties for User-Agent
            String userAgentHeader = "Profile=" + System.getProperty("microedition.profiles") +
                    " Configuration=" + System.getProperty("microedition.configuration");

            // set header
            httpConnection.setRequestProperty("User-Agent", userAgentHeader);
        } catch (IOException ioException) {
            // handle exception getting request property
            ioException.printStackTrace();
        }
    }

    // open DataInputStream to receive data
    private String getData(HttpConnection httpConnection) throws IOException {
        String data = ""; // stores data

        // open input stream from connection
        DataInputStream dataInputStream = new DataInputStream(httpConnection.openInputStream());

        int inputCharacter = dataInputStream.read();

        // read all data
        while (inputCharacter != -1) {
            data = data + (char) inputCharacter;
            inputCharacter = dataInputStream.read();
        }

        dataInputStream.close(); // close stream

        return data;
    }
*/
}
