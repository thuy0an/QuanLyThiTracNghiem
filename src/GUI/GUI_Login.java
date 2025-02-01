
package GUI;

import BUS.BUS_User;
import GUI.Component.InputForm;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class GUI_Login extends JFrame {
    JPanel pnlMain, pnlLogin;
    JLabel lblPc, lblTitleLogin, lblLogin, lblDangKy;
    InputForm txtUsername, txtPass;
    BUS.BUS_User userBUS = new BUS_User();

    public GUI_Login() {
        this.setSize(new Dimension(1000, 600));
        this.setLayout(new BorderLayout(0, 0));
        this.setTitle("HỆ THỐNG QUẢN LÝ THI TRẮC NGHIỆM");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InitComponent();
        this.setLocationRelativeTo(null);
    }

    private void InitComponent() {
        pnlMain = new JPanel();
        // đưa hình vào
        JPanel pnlPic = new JPanel(new GridBagLayout());
        lblPc = new JLabel();
        lblPc.setIcon(new FlatSVGIcon("./img/login.svg"));
        pnlPic.setPreferredSize(new Dimension(500, 600));
        pnlPic.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        pnlPic.add(lblPc, gbc);
        this.add(pnlPic, BorderLayout.EAST);

        // tiêu đề
        pnlMain = new JPanel();
        pnlMain.setBackground(Color.WHITE);
        pnlMain.setBorder(new EmptyBorder(50, 0, 0, 0));

        pnlMain.setPreferredSize(new Dimension(500, 600));
        pnlMain.setLayout(new FlowLayout(1, 0, 10));

        lblTitleLogin = new JLabel("ĐĂNG NHẬP");
        lblTitleLogin.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 24));
        pnlMain.add(lblTitleLogin);
        // nội dung
        JPanel pnlNoiDung = new JPanel();
        pnlNoiDung.setBackground(Color.WHITE);
        pnlNoiDung.setLayout(new GridLayout(2, 1));
        pnlNoiDung.setBorder(new EmptyBorder(0, 0, 20, 0));
        txtUsername = new InputForm("Tên đăng nhập");
        pnlNoiDung.add(txtUsername);
        txtPass = new InputForm("Mật khẩu", "password");
        pnlNoiDung.add(txtPass);
        pnlMain.add(pnlNoiDung);
        // button
        lblLogin = new JLabel("ĐĂNG NHẬP");
        lblLogin.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 16));
        lblLogin.setForeground(Color.white);

        pnlLogin = new JPanel();
        pnlLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlLogin.putClientProperty(FlatClientProperties.STYLE, "arc: 100");
        pnlLogin.setBackground(Color.BLACK);
        pnlLogin.setPreferredSize(new Dimension(400, 50));
        pnlLogin.setLayout(new FlowLayout(1, 0, 15));
        pnlLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                ValidateLogin();
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                pnlLogin.setBackground(new Color(50, 50, 50));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                pnlLogin.setBackground(Color.BLACK);
            }

        });
        pnlLogin.add(lblLogin);
        pnlMain.add(pnlLogin);

        // thêm phân cách DN - DK
        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(380, 20));
        separator.setForeground(Color.LIGHT_GRAY);
        pnlMain.add(separator);
        // thêm phần đăng ký
        lblDangKy = new JLabel("Bạn chưa có tài khoản?        ");
        lblDangKy.setFont(new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 14));
        lblDangKy.setBounds(50, 400, 300, 30);
        pnlMain.add(lblDangKy);

        JPanel pnlLogup = new JPanel();
        pnlLogup.putClientProperty(FlatClientProperties.STYLE, "arc: 100");
        pnlLogup.setBackground(Color.decode("#2a48aa"));
        pnlLogup.setPreferredSize(new Dimension(100, 45));
        pnlLogup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlLogup.setLayout(new FlowLayout(1, 0, 10));

        JLabel lblLogup = new JLabel("Đăng ký");
        lblLogup.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 16));
        lblLogup.setForeground(Color.white);

        pnlLogup.add(lblLogup);
        pnlLogup.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                new GUI_SignUp();
                CloseWin();
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                pnlLogup.setBackground(Color.decode("#5070cc"));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                pnlLogup.setBackground(Color.decode("#2a48aa"));
            }
        });
        pnlLogup.add(lblLogup);
        pnlMain.add(pnlLogup);

        txtUsername.getTxtForm().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    ValidateLogin();
            }
        });

        txtPass.getTxtPass().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    ValidateLogin();
            }
        });

        this.add(pnlMain, BorderLayout.WEST);
        this.setVisible(true);

    }

    private void ValidateLogin() {
        String userName = txtUsername.getText().trim();
        String pass = txtPass.getPass().trim();
        if (userName.isEmpty() || pass.isEmpty())
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ", "Cảnh báo!",
                    JOptionPane.WARNING_MESSAGE);
        else {
            int check = userBUS.login(userName, pass);
            if (check == 0)
                JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không đúng", "Cảnh báo!",
                        JOptionPane.ERROR_MESSAGE);
            else {
                JOptionPane.showMessageDialog(this, "Thành công", "Cảnh báo!", JOptionPane.INFORMATION_MESSAGE);
                DTO.DTO_User nguoiDung = userBUS.getInfo(userName, pass);
                this.dispose();
                GUI_MainFrm menu = new GUI_MainFrm(nguoiDung); // ném tk vào đây
                menu.setVisible(true);
            }
        }
    }

    private void CloseWin() {
        this.dispose();
    }

    private static void DownFont() {
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        UIManager.put("PasswordField.showRevealButton", true);
        UIManager.put("PopupMenu.borderCornerRadius", 80);
    }

    public static void main(String[] args) {
        DownFont();
        GUI_Login login = new GUI_Login();
        login.setVisible(true);
    }

}
