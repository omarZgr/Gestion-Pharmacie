module com.application.start {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    opens com.application.start.GereMenu.GestionUser.GereAction.Suivi ;
    opens  com.application.start.GereMenu.GestionFournisseur;
    opens  com.application.start.GereMenu.GestionFournisseur.GereAction ;
    opens com.application.start.GereMenu.GestionFournisseur.GereAction.Next ;

    requires javafx.graphics;
    requires com.jfoenix;


    opens com.application.start.GereMenu.GestionUser ;
    opens com.application.start.GereMenu.GestionUser.GereAction ;

    opens com.application.start to javafx.fxml;
    exports com.application.start;
    opens com.application.start.GereMenu.GestionUser.GereAction.Add;
    opens com.application.start.GereMenu.GestionUser.GereAction.Modifiy;
    opens com.application.start.GereMenu.GestionUser.GereAction.Remove;
}