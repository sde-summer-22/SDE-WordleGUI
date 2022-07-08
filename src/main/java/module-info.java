module edu.virginia.cs.hw5.wordlegui {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.virginia.cs.hw5 to javafx.fxml;
    exports edu.virginia.cs.hw5;
}