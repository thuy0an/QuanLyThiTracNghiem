package GUI.Menu;

import BUS.BUS_Exam;
import BUS.BUS_Result;
import BUS.BUS_Test;
import ConnectDB.JDBCUtil;
import DTO.DTO_Exam;
import DTO.DTO_Test;
import GUI.CRUD.ChiTietBaiThi;
import GUI.CRUD.ChiTietThongKe;
import GUI.Component.MenuTaskBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JPanel;
import GUI.Component.pnlBaiThi;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class QuanLyThongKe extends JPanel {
    private ArrayList<pnlBaiThi> listPnl = new ArrayList<>();
    private GUI.GUI_MainFrm main;
    private javax.swing.JPanel pnlContent;
    private BUS.BUS_Test testBUS =  new BUS_Test();
    private BUS_Exam examBUS = new BUS_Exam();
    private BUS_Result resBUS = new BUS_Result();
    private MenuTaskBar menuTask;
   
    
    public QuanLyThongKe(GUI.GUI_MainFrm main, MenuTaskBar menuTask) {
        this.menuTask = menuTask;
        this.main = main;
        initComponents();
        LoadBaiThi();
        
        txtSearch2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = txtSearch2.getText().trim();
                System.out.println(searchText);
                searchBaiThi(searchText);
            }
        });
    }
    
    private void LoadUIBaiThi(ArrayList<DTO_Test> list){
        ArrayList<DTO_Test> listTest = new ArrayList<>();
        HashSet<String> check = new HashSet<>();
        for (DTO_Test t : list)
            if (check.add(t.getTestCode()))
                listTest.add(t);

        for(DTO_Test item : listTest){
            int count = resBUS.CountResult(item.getTestCode());
            pnlBaiThi pnl = new pnlBaiThi(item.getTestCode(), item.getTestTitle(), count, item.getTestDate().toString());
            pnl.setPreferredSize(new java.awt.Dimension(250, 150));
            listPnl.add(pnl);
            
            pnlContent.add(pnl);
            
            pnl.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    ThongKeBaiThi(item.getTestCode());
                }
            });     
        }
        pnlContent.revalidate();
        pnlContent.repaint();
    }

    private void LoadBaiThi() {
        ArrayList<DTO_Test> list = testBUS.getAllData();
        ArrayList<DTO_Test> listTest = new ArrayList<>();
        HashSet<String> check = new HashSet<>();
        for (DTO_Test t : list)
            if (check.add(t.getTestCode()))
            listTest.add(t);
        listPnl.clear();
        pnlContent.removeAll();
        LoadUIBaiThi(listTest);

    }

    private void searchBaiThi(String searchText){
        ArrayList<DTO_Test> listTest = testBUS.searchData(searchText);
        listPnl.clear();
        pnlContent.removeAll();

        LoadUIBaiThi(listTest);
    }

    private void ThongKeBaiThi(String testCode){
        ChiTietThongKe thongke = new ChiTietThongKe(this.main, this.menuTask, testCode);
        this.main.changePages(thongke);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlSearch = new javax.swing.JPanel();
        txtSearch2 = new com.raven.suportSwing.TextField();
        btnReset = new com.raven.suportSwing.MyButton();
        pnlListContainer = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1744, 938));

        pnlSearch.setBackground(new java.awt.Color(255, 255, 255));
        pnlSearch.setPreferredSize(new java.awt.Dimension(1027, 250));

        txtSearch2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSearch2.setLabelText("Tìm bài thi");
        txtSearch2.setPreferredSize(new java.awt.Dimension(300, 50));

        btnReset.setBackground(new java.awt.Color(204, 255, 255));
        btnReset.setText("Reset");
        btnReset.setBorderColor(new java.awt.Color(153, 255, 255));
        btnReset.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReset.setRadius(20);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSearchLayout = new javax.swing.GroupLayout(pnlSearch);
        pnlSearch.setLayout(pnlSearchLayout);
        pnlSearchLayout.setHorizontalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1236, Short.MAX_VALUE))
        );
        pnlSearchLayout.setVerticalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addContainerGap(91, Short.MAX_VALUE)
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlContent = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        jScrollPane1.setViewportView(pnlContent);
        
        pnlListContainer.setLayout(new BorderLayout());
        pnlListContainer.add(jScrollPane1, BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 1744, Short.MAX_VALUE)
            .addComponent(pnlListContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlListContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE))
        );
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
        txtSearch2.setText("");
        LoadBaiThi();
    }
    

    // Variables declaration - do not modify
    private com.raven.suportSwing.MyButton btnReset;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlListContainer;
    private javax.swing.JPanel pnlSearch;
    private com.raven.suportSwing.TextField txtSearch2;
    // End of variables declaration
}
