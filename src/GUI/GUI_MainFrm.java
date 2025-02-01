
package GUI;

import GUI.Component.MenuTaskBar;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// khởi tạo giao diện admin hoặc người dùng thường
// setting UI chính cho giao diện
public class GUI_MainFrm extends JFrame{
    public DTO.DTO_User user;
    public JPanel mainContent;
    public MenuTaskBar menuTaskBar;
    
    public GUI_MainFrm(){
        initComponent();
    }
    public GUI_MainFrm(DTO.DTO_User user){
        this.user = user;
        loadUI();
        initComponent();
    }
    
    private  void initComponent(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        DisplayMode mode = gd.getDisplayMode();
        int screenWidth = mode.getWidth();
        int screenHeight = mode.getHeight();
        this.setSize(screenWidth, screenHeight);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));
        this.setTitle("HỆ THỐNG QUẢN LÝ THI TRẮC NGHIỆM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuTaskBar = new MenuTaskBar(this, user);
        menuTaskBar.setPreferredSize(new Dimension(250, 1400));
        this.add(menuTaskBar, BorderLayout.WEST);
        mainContent = new JPanel();
        mainContent.setBackground(Color.WHITE);
        mainContent.setLayout(new BorderLayout(0, 0));
        this.add(mainContent, BorderLayout.CENTER);      
        
    }
    
    public void changePages(JPanel pn) {
        mainContent.removeAll();
        mainContent.add(pn);
        mainContent.repaint();
        mainContent.validate();
    }
    
    private void loadUI(){
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        FlatLightLaf.setup();
        UIManager.put("Table.showVerticalLines", false);
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("TextComponent.arc", 5);
        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        UIManager.put("Button.iconTextGap", 10);
        UIManager.put("PasswordField.showRevealButton", true);
        UIManager.put("Table.selectionBackground", new Color(240, 247, 250));
        UIManager.put("Table.selectionForeground", new Color(0, 0, 0));
        UIManager.put("Table.scrollPaneBorder", new EmptyBorder(0, 0, 0, 0));
        UIManager.put("Table.rowHeight", 40);
        UIManager.put("TabbedPane.selectedBackground", Color.white);
        UIManager.put("TableHeader.height", 40);
        UIManager.put("TableHeader.font", UIManager.getFont("h4.font"));
        UIManager.put("TableHeader.background", new Color(242, 242, 242));
        UIManager.put("TableHeader.separatorColor", new Color(242, 242, 242));
        UIManager.put("TableHeader.bottomSeparatorColor", new Color(242, 242, 242));
        UIManager.put("ComboBox.buttonHoverArrowColor", new Color(135,206,235));
        UIManager.put("ComboBox.buttonSeparatorWidth", 5);
        UIManager.put("ComboBox.buttonSeparatorColor", new Color(135,206,235));
        UIManager.put("Label.background", new Color(255,0,0));  
    }
    
}
