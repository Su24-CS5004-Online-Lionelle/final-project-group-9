## Initial UML Diagram 

```mermaid
---
title : NBA Player Stats Application
---
classDiagram
    direction TB
    NBAStatDriver --> Controller : Uses
    NBAStatDriver --> IView : Uses
    NBAStatDriver --> IModel : Uses
    View ..|> IView : Implements

    Model ..|> IModel : Implements
    Model --> Player : Uses
    Model --> NetUtils : Uses
    Model --> DataFormatter : Uses 
    DataFormatter --> Formats : Uses
    
    %% Driver for application
    class NBAStatDriver {
        - NBAStatDriver()
        + main(String[] args) : void
 }
 
     class Controller {
        - view : IView
        - model : IModel
        - String playerName = "all"
        + processCommands(String command) : String
        - getPlayer(String playerName, boolean add)
        - listAllPlayers(String playerName) : String
        - generateRoster(String ) : String
        - filterPlayers(String filterOperation) : String
        - removePlayerFromRoster(String playerName) : String
        - exportPlayers() : void
 }
 
    class IView {
        <<interface>>
        display(String text) : void
        setListeners(ActionListener clicks) : void
        getInputString() : String
        clearInputField() : void
        clearDisplay() : void
        start() : void
 }
 
    class View {
        - display : JLabel
        - statDropDown : JComboBox~String~
        - lookupButton : JButton
        - listAllButton : JButton
        - removeButton : JButton
        - exportButton : JButton
        - clearButton : JButton
        - exitButton : JButton
        - userInput : JTextField
        - lowerTextArea : JTextArea
        
        + View(String caption)
        + setListeners(ActionListener clicks) : void
        + display(String text) : void
        + getInputString() : String
        + clearInputField() : void
        + clearDisplay() : void
        + start() : void
        + getHelp() : String
 }
 
    %% Model Package
    %% Model interface
    class IModel {
        <<interface>>
        String DATABASE = "data/nbarecords.json"
        + getPlayer(String playerName) : Player
        + getUserRoster() : Set~Player~
        + getLeagueRoster() : Set~Player~
 }
 
    %% Model for creating all and saved roster of players
    class Model {
        - UserRoster : Set~Player~
        - LeagueRoster : Set~Player~
        + Model(String databaseFile)
        + getPlayer(String playerName) : Player
        + getUserRoster() : Set~Player~
        + getLeagueRoster() : Set~Player~
 }
 
 
    %% Record class
    class Player {
        - name : String
        - age : Integer
        - team : String
        - conference : String
        - pointsPerGame : Double
        - reboundsPerGame : Double
        - assistsPerGame : Double
        - blocksPerGame : Double
        - stealsPerGame : Double
        - fieldGoalPercentage : Double
        - threePointPercentage : Double
    }
 
    %% Pulls API data
    %% Model/Net package
    class NetUtils {
        - String API_URL_FORMAT = "https://ipapi.co/%s/%s/"
        - NetUtils()
        + getApiUrl(String ip) String
        + getApiUrl(String ip, Formats format) String
        + lookUpIp(String hostname) String
        + getUrlContents(String urlStr) InputStream
        + getIpDetails(String ip) InputStream
        + getIpDetails(String ip, Formats format) InputStream 
        }
 
    %% Formats data pulled from API to Pretty, XML, JSON, CSV
    %% In model package
    class DataFormatter {
        - DataFormatter()
        - prettyPrint : void
        - prettySingle(NBARecord record, PrintStream out) : void
        - writeXmlData(Collection<Player> records, OutputStream out) : void
        - writeJsonData(Collection<Player> records, OutputStream out) : void
        - writeCSVData(Collection<Player> records, OutputStream out) : void
        + write(Collection<Player> records, Formats format) : void
 }
    %% In model package
    class Formats {
        <<enumeration>>
        - JSON
        - XML
        - CSV
        - PRETTY
    }
```

## Final UML Diagram

```mermaid
---
title : NBA Player Stats Application
---
classDiagram
    direction TB
    NBAStatDriver --> Controller: Uses
    NBAStatDriver --> IView: Uses
    NBAStatDriver --> IModel: Uses
    JFrameView ..|> IView: Implements
    Controller --> IModel: Uses
    Controller --> IView: Uses
    Controller --> ColumnData: Uses
    Controller --> Format: Uses
    Model ..|> IModel: Implements
    Model --> Player: Uses
    Model --> PlayerBean: Uses
    Model --> NetUtils: Uses
    Model --> DataFormatter: Uses
    Model --> ColumnData: Uses
    Model --> Operations: Uses
    Model --> Filters: Uses
    DataFormatter --> Formats: Uses
    DataFormatter --> PlayerXMLWrapper: Uses
    PlayerBackground --> IModel: Is a internal record of
    PlayerAverages --> IModel: Is a internal record of
    Team --> IModel: Is a internal record of
    Model --> PlayerSort: Uses
    PlayerSort --> PlayerSortStrategy: Uses
    PlayerSort --> Player: Uses
    PlayerSort --> ColumnData: Uses
    Filters --> Operations: Uses
    JFrameView --> Background: Uses
    PlayerSortStrategy --> FirstNameAscending: uses
    PlayerSortStrategy --> FirstNameDescending: uses
    PlayerSortStrategy --> LastNameAscending: uses
    PlayerSortStrategy --> LastNameDescending: uses
    PlayerSortStrategy --> PositionAscending: uses
    PlayerSortStrategy --> PositionDescending: uses
    PlayerSortStrategy --> HeightAscending: uses
    PlayerSortStrategy --> HeightDescending: uses
    PlayerSortStrategy --> DraftYearAscending: uses
    PlayerSortStrategy --> DraftYearDescending: uses
    PlayerSortStrategy --> DraftRoundAscending: uses
    PlayerSortStrategy --> DraftRoundDescending: uses
    PlayerSortStrategy --> DraftPickAscending: uses
    PlayerSortStrategy --> DraftPickDescending: uses
    PlayerSortStrategy --> TeamAscending: uses
    PlayerSortStrategy --> TeamDescending: uses
    PlayerSortStrategy --> ConferenceAscending: uses
    PlayerSortStrategy --> ConferenceDescending: uses
    PlayerSortStrategy --> PpgAscending: uses
    PlayerSortStrategy --> PpgDescending: uses
    PlayerSortStrategy --> RpgAscending: uses
    PlayerSortStrategy --> RpgDescending: uses
    PlayerSortStrategy --> ApgAscending: uses
    PlayerSortStrategy --> ApgDescending: uses
    PlayerSortStrategy --> BpgAscending: uses
    PlayerSortStrategy --> BpgDescending: uses
    PlayerSortStrategy --> SpgAscending: uses
    PlayerSortStrategy --> SpgDescending: uses
    PlayerSortStrategy --> MpgAscending: uses
    PlayerSortStrategy --> MpgDescending: uses
    PlayerSortStrategy --> FgpAscending: uses
    PlayerSortStrategy --> FgpDescending: uses
    PlayerSortStrategy --> FtpAscending: uses
    PlayerSortStrategy --> FtpDescending: uses
    PlayerSortStrategy --> Fg3pAscending: uses
    PlayerSortStrategy --> Fg3pDescending: uses
%% Driver for application
    class NBAStatDriver {
        - NBAStatDriver()
        + main(String[] args): void
    }

    class Controller {
        - IView view
        - IModel model
        - Set<Player> filterSortedSet
        - String testFilePath
        - JFileChooser fileChooser
        + Controller(IView view, IModel model)
        + actionPerformed(ActionEvent e) void
        + searchMethod(String inputString, ColumnData selectedFilter, boolean selectedSort) void
        + addMethod(String inputString, ColumnData selectedFilter, boolean selectedSort) void
        + removeMethod(String inputString) void
        + showRosterMethod() void
        + exportMethod() void
        + loadMethod(String inputString) void
        + clearMethod() void
        + displayPlayers(Set<Player> players, ColumnData filter) void
        + formatPlayer(Player player, ColumnData filter) String
        + determineFormat(FileNameExtensionFilter fileFilter) Format
        + setTestFilePath(String testFilePath) void
        + setFileChooser(JFileChooser fileChooser) void
        + getFileExtension(String filePath) String
        + timer() void
        + getModel() IModel
        + setModel(IModel model) void
        + getFilterSortedSet() Set<Player>
        + setFilterSortedSet(Set<Player> filteredSet) void
    }

    class IView {
        <<interface>>
        display(String text) void
        setListeners(ActionListener clicks) void
        getInputString() String
        getFilterChoice() ColumnData
        getSortChoice() boolean
        clearInputField() void
        clearDisplay() void
        getHelp() String
        start() void
    }

    class JFrameView {
        - JLabel prompt
        - JLabel title
        - JTextField input
        - JComboBox~String~ filterChoice
        - JComboBox sortChoice
        - List~String~ items
        - List~String~ ~ sortOrder
        - JButton searchButton
        - JButton showListButton
        - JButton loadButton
        - JButton exportButton
        - JButton clearButton
        - JButton exitButton
        - JButton addButton
        - JButton removeButton
        - JButton helpButton
        - JTextArea lowerTextArea
        + JFrameView(String caption)
        - createImageLabel(String imagePath, int width, int height)
        + display(String text): void
    }

    class Background {
        - Image backgroundImage
        + Background(String imagePath)
        # paintComponent(Graphics g)
    }

%% Model Package
%% Player Average record class
    class PlayerAverages {
        <<record>>
        PlayerAverages(double pts, double ast, double turnover, double pf, double reb, double stl, double blk,
        double fg_pct, double fg3_pct, double ft_pct, String min, double games_played, int player_id)
    }

%% Player background record class
    class PlayerBackground {
        <<record>>
        PlayerBackground(int id, String first_name, String last_name, String position, String height,
        int draft_year, int draft_round, int draft_number, Team team)
    }

%% Model interface
    class IModel {
        <<interface>>
        String DATABASE = "data/database.json"
        getRoster() Set~Player~
        getAllPlayers() Set~Player~
        getFilePath() String
        setFilePath(String filePath) void
        toString(Player player) String
        createNBARoster() Set~Player~
        beanToPlayer(List~PlayerBean~beanList) Set~Player~
        filterSortNBARoster(String filter) Set~Player~
        filterSortNBARoster(String filter, ColumnData sortOn) Set~Player~
        filterSortNBARoster(String filter, ColumnData sortOn, boolean ascending) Set~Player~
        buildRoster(Set<Player> filterSortedSet, String nameOrRange) void
        removeFromRoster(String nameOrRange) void
        createPlayer(PlayerBackground background, PlayerAverages seasonAverages) Player
    }

%% Model for creating all and saved roster of players
    class Model {
        - String filePath
        - Set~Player~ roster
        - Set~Player~ NBAROSTER = new TreeSet<>(PlayerSortStrategy.getSort(ColumnData.FIRST_NAME));
        + Model()
        + Model(String filePath)
        + setRoster(Set~Player~ roster) void
    }

%% Player data class
    class Player {
        - String first_name
        - String last_name
        - String position
        - String height
        - int draftYear
        - int draftRound
        - int draftPick
        - String team
        - String conference
        - double ppg
        - double rpg
        - double apg
        - double bpg
        - double spg
        - String mpg
        - double fgp
        - double ftp
        - double fg3p
        + Player()
        + Player(String first_name, String last_name, String position, String height, int draftYear, int draftRound, int draftPick, String team, String conference, double ppg, double rpg, double apg, double bpg, double spg, String mpg, double fgp, double ftp, double fg3p)
        + getFirstName() String
        + getLastName() String
        + getPosition() String
        + getHeight() String
        + getDraftYear() int
        + getDraftRound() int
        + getDraftPick() int
        + getTeam() String
        + getConference() String
        + getPpg() double
        + getRpg() double
        + getApg() double
        + getBpg() double
        + getSpg() double
        + getMpg() String
        + getFgp() double
        + getFtp() double
        + getFg3p() double
        + toString() String
    }

%% Record class
    class Player {
        - name: String
        - age: Integer
        - team: String
        - conference: String
        - pointsPerGame: Double
        - reboundsPerGame: Double
        - assistsPerGame: Double
        - blocksPerGame: Double
        - stealsPerGame: Double
        - fieldGoalPercentage: Double
        - threePointPercentage: Double
    }

%%Sort/Filter logic
%% Enum for player attributes/categories
    class ColumnData {
        <<enumeration>>
        - FIRST_NAME("First name")
        - LAST_NAME("Last name")
        - POSITION("Position")
        - HEIGHT("Height")
        - DRAFTYEAR("Draft year")
        - DRAFTROUND("Draft round")
        - DRAFTPICK("Draft pick")
        - TEAM("Team")
        - CONFERENCE("Conference")
        - PPG("Points per game")
        - RPG("Rebounds per game")
        - APG("Assists per game")
        - BPG("Blocks per game")
        - SPG("Steals per game")
        - MPG("Minutes per game")
        - FGP("Field goal percentage")
        - FTP("Free throw percentage")
        - FP3P("3 point percentage")
        - columnName: String
        + ColumnData(String columnName)
        - getColumnName() String
        + fromColumnName(String columnName) ColumnData
        + fromString(String name) ColumnData
    }

%% Filter class that uses Operations enum
    class Filters {
        - int ZERO_INT = 0
        - Filters()
        + getFilter(Player player, ColumnData type, Operations op, String val) boolean
        - filterString(String name, Operations op, String value) boolean
        - filterNumericalValues(double number, Operations op, double doubleValue) boolean
        - convertHeightToDouble(String playerOne, Operations op, String playerTwo) boolean
        - convertInt(int number, Operations op, String value) boolean
        - convertDouble(double number, Operations op, String value) boolean
    }

%% Enum class for operations
    class Operations {
        <<enumeration>>
        EQUALS("==")
        NOT_EQUALS("!=")
        CONTAINS("~=")
        GREATER_THAN_EQUALS(">=")
        LESS_THAN_EQUALS("<=")
        GREATER_THAN(">")
        LESS_THAN("<")
        - String operator
        Operations(String value)
        + getOperator() String
        + getOperatorFromStr(String str) Operations
    }

%% PlayerSort class generates set of sorted players based on sort type
    class PlayerSort {
        - PlayerSort()
        + sortPlayers(Set~Player~ players, String sortType) Set~Player~
        + sortPlayers(Set~Player~ players, String sortType, boolean direction) Set~Player~
    }

%% Responsible for sorting and comparators
    class PlayerSortStrategy {
        - int ZERO_INT = 0
        - PlayerSortStrategy()
        + getSort(ColumnData sortType) Comparator~Player~
        + getSort(ColumnData sortType, boolean direction) Comparator~Player~
        - getHeightInInches(Player player) int
    }

%% Pulls API data
%% Model/Net package
    class NetUtils {
        - String API_URL = "https://api.balldontlie.io/v1"
        - String API_KEY = "d8754d2a-af6d-42ab-a674-d3ab4d504ad9"
        - NetUtils()
        - urlConnection(String endpoint) HttpURLConnection
        - getUrlContents(HttpURLConnection connection) String
        + getAPlayer(String name) PlayerBackground
        + getAPlayer(String name, String lastName) PlayerBackground
        + getPlayerDataString(String endpoint) String
        + fetchPlayers() List~PlayerBackground~
        + fetchSeasonAverages(String id) IModel.PlayerAverages
    }

%% Formats data pulled from API to Pretty, XML, JSON, CSV
%% In model package
    class DataFormatter {
        - DataFormatter()
        - prettyPrint(Collection<Player> records, OutputStream out) void
        - prettySingle(Player record, PrintStream out) void
        - writeXmlData(Collection<Player> records, OutputStream out) void
        - writeJsonData(Collection<T> records, OutputStream out) void
        - writeCSVData(Collection<Player> records, OutputStream out) void
        - mapperWriter(ObjectMapper mapper, OutputStream out, Collection<T> records) void
        + write(Collection<Player> records, Formats format) void
    }
%% In model package
    class Formats {
        <<enumeration>>
        - JSON
        - XML
        - CSV
        - PRETTY
        + containsValues(String value) Format
    }

    class FirstNameAscending {
        + compare(Player o1, Player o2) int
    }

    class FirstNameDescending {
        + compare(Player o1, Player o2) int
    }

    class LastNameAscending {
        + compare(Player o1, Player o2) int
    }

    class LastNameDescending {
        + compare(Player o1, Player o2) int
    }

    class PositionAscending {
        + compare(Player o1, Player o2) int
    }

    class PositionDescending {
        + compare(Player o1, Player o2) int
    }

    class HeightAscending {
        + compare(Player o1, Player o2) int
    }

    class HeightDescending {
        + compare(Player o1, Player o2) int
    }

    class DraftYearAscending {
        + compare(Player o1, Player o2) int
    }

    class DraftYearDescending {
        + compare(Player o1, Player o2) int
    }

    class DraftRoundAscending {
        + compare(Player o1, Player o2) int
    }

    class DraftRoundDescending {
        + compare(Player o1, Player o2) int
    }

    class DraftPickAscending {
        + compare(Player o1, Player o2) int
    }

    class DraftPickDescending {
        + compare(Player o1, Player o2) int
    }

    class TeamAscending {
        + compare(Player o1, Player o2) int
    }

    class TeamDescending {
        + compare(Player o1, Player o2) int
    }

    class ConferenceAscending {
        + compare(Player o1, Player o2) int
    }

    class ConferenceDescending {
        + compare(Player o1, Player o2) int
    }

    class PpgAscending {
        + compare(Player o1, Player o2) int
    }

    class PpgDescending {
        + compare(Player o1, Player o2) int
    }

    class RpgAscending {
        + compare(Player o1, Player o2) int
    }

    class RpgDescending {
        + compare(Player o1, Player o2) int
    }
    class ApgAscending {
        + compare(Player o1, Player o2) int
    }

    class ApgDescending {
        + compare(Player o1, Player o2) int
    }

    class BpgAscending {
        + compare(Player o1, Player o2) int
    }

    class BpgDescending {
        + compare(Player o1, Player o2) int
    }
    class SpgAscending {
        + compare(Player o1, Player o2) int
    }

    class SpgDescending {
        + compare(Player o1, Player o2) int
    }

    class MpgAscending {
        + compare(Player o1, Player o2) int
    }

    class MpgDescending {
        + compare(Player o1, Player o2) int
    }

    class FgpAscending {
        + compare(Player o1, Player o2) int
    }

    class FgpDescending {
        + compare(Player o1, Player o2) int
    }

    class FtpAscending {
        + compare(Player o1, Player o2) int
    }

    class FtpDescending {
        + compare(Player o1, Player o2) int
    }

    class Fg3pAscending {
        + compare(Player o1, Player o2) int
    }

    class Fg3pDescending {
        + compare(Player o1, Player o2) int
    }
```