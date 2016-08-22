
/*
 * Main.java
 *
 * Created on 20 / agost / 2005, 23:25
 * last update 18/12/2008
 * last update 04/09/2009
 * !! fer una de les finestres de debug
 * control finestres fora del desktop (SCREENX,SCREENY)
 * habilitar botons (minimitzar,restaurar,maximitzar,tancar tenint en compte si pot_resize)
 * afegir una consola
 * posar nom de l'aplicacio en el desktop
 * fer pantalla dialeg.. entrar dades.
 * pensar en fer botons,menu-texte,
 * 
 * fer clase per carregar icones i imatges propies del joc
 * Llegir el disseny de la pagina des de un xml 
 * label,buton,checkbox,menu... etc.
 * 
 * fer aplicaci� d'exemple.. dissenyador de pantalles pel trenalk 
 * poder gravar el disseny del tren
 * pantalla disseny
 * pantalla menu
 *      load
 *      save
 *      test
 *      design
 *      
 * base de dades SQl, xml
 *  
 * pantalla eines
 * en funcio de la tria del menu es carreguen uns botons o no a la barra d'eines
 
  Menu1.NewMenu('Menu2',aDWN,Blanc,Negre,BlauCel,Negre,BlancB,Negre,BlauCelB,Negre,Gris,Negre);
    Menu1.AddItem('Menu2','A',' Marxar ',aHor);
    Menu1.AddFunc2Item('A',marxar);       {Assigna una funcio }
    Menu1.Open('.');
 
 */
//firmar .jars
package visualk.lmk;

import javax.swing.*;
import visualk.lmk.grf.Desktop;
import visualk.lmk.grf.Finestra;
import visualk.lmk.grf.WinDlgMsg;
import visualk.lmk.res.func.IFunctions;
import visualk.lmk.usr.CanvasButtons;
import visualk.lmk.usr.CanvasGlobal;
import visualk.lmk.usr.CanvasMenu;
import visualk.lmk.usr.CanvasWindow;

public class Main extends JApplet {

    private static final long serialVersionUID = 5892639554380164162L;

    public static Desktop myDesktop;

    CanvasWindow cwApp = new CanvasWindow();
    CanvasMenu cmApp = new CanvasMenu();
    CanvasButtons cbApp = new CanvasButtons();

    IFunctions cdApp = new WinDlgMsg("Hola Penya!");

    CanvasGlobal cg = new CanvasGlobal(cwApp, cmApp, cbApp);

    public void CreateDesktop() {
        //ha de carregar un desktop en concret

        Finestra fin4 = new Finestra(cdApp, 260, 200, 190, 85, "Dialog", true, false, true, true);
        myDesktop.AddWin(fin4);

        Finestra fin;
        fin = new Finestra(cwApp, 5, 5, 620, 545, "Drawing corporation in vision", true, true, true, false);
        myDesktop.AddWin(fin);

        Finestra fin2;
        fin2 = new Finestra(cmApp, 635, 5, 161, 350, "Menu", true, false, true, false);
        myDesktop.AddWin(fin2);

        Finestra fin3 = new Finestra(cbApp, 635, 365, 161, 190, "Eines", true, true, true, false);
        myDesktop.AddWin(fin3);

    }

    public void init() {
        myDesktop = new Desktop(800, 600);
        CreateDesktop();
        setContentPane(myDesktop);
        myDesktop.start();
        myDesktop.outmsg("init.fin");
    }
}
