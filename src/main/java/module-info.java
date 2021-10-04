module com.tsybulko.cos1_dop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tsybulko.cos1_dop to javafx.fxml;
    exports com.tsybulko.cos1_dop;
}