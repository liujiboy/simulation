package view;

import javax.swing.*;
import java.awt.*;

public class SimulationParameterPanel extends JPanel {
    private JLabel humanSizeLabel=new JLabel("人口：");
    private JTextField humanSizeTextField=new JTextField(5);
    private JLabel iRatioLabel=new JLabel("感染比例：");
    private JTextField iRatioTextField=new JTextField(5);
    private  JLabel pLabel=new JLabel("传染率：");
    private JTextField pTextField=new JTextField(5);
    private JLabel speedLabel=new JLabel("速度（ms）：");
    private JTextField speedField=new JTextField(5);
    public SimulationParameterPanel()
    {
        setLayout(new GridLayout(4,1));
        add(createParameterInput(humanSizeLabel,humanSizeTextField));
        add(createParameterInput(iRatioLabel,iRatioTextField));
        add(createParameterInput(pLabel,pTextField));
        add(createParameterInput(speedLabel,speedField));

    }
    private JPanel createParameterInput(JLabel label,JTextField textField)
    {
        JPanel p=new JPanel();
        p.setLayout(new FlowLayout(FlowLayout.LEFT));
        label.setPreferredSize(new Dimension(100,50));
        p.add(label);
        p.add(textField);
        return p;
    }
    public int getHumanSize()
    {
        return Integer.parseInt(humanSizeTextField.getText());
    }
    public double getP()
    {
        return Double.parseDouble(pTextField.getText());
    }
    public double getIRatio()
    {
        return Double.parseDouble(iRatioTextField.getText());
    }
    public int getSpeed()
    {
        return Integer.parseInt(speedField.getText());
    }

}
