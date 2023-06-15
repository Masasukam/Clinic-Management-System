package ui.tools;

import exceptions.TooManyPatients;
import hospital.Doctor;
import hospital.Patient;
import hospital.Save;
//import sun.applet.Main;

import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainView extends JFrame {
    JFrame frame;
    Doctor doctor;

    public MainView(Doctor doctor) {
        frame = new JFrame("Clinic System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.doctor = doctor;
        frame.setSize(500, 500);
        try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("./data/hospital.png")))));

        } catch (IOException e) {
            e.printStackTrace();
        }
        initializeButtons();
        pack();
        frame.setLayout(null);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initializeButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JButton addPatient = new JButton("Add Patient");

        JButton removePatient = new JButton("Remove Patient");

        JButton searchPatient = new JButton("Search Patient");

        JButton modifyPatient = new JButton("Modify Patient");

        JButton save = new JButton("Save");

        List<JButton> buttons = Arrays.asList(new JButton[]{addPatient, removePatient, searchPatient, modifyPatient,
                save});
        panel.setSize(650, 2000);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        for (JButton button: buttons) {
            button.setSize(300, 120);
            panel.add(button);
        }
        registerButtons(addPatient, removePatient, searchPatient, modifyPatient, save);
        frame.add(panel, BorderLayout.CENTER);
    }

    private void registerButtons(JButton addPatient, JButton removePatient, JButton searchPatient,
                                 JButton modifyPatient, JButton save) {
        addPatient.setBounds(200,50,300,120);
        registerAddPatient(addPatient);
        removePatient.setBounds(200,250,300,120);
        registerRemovePatient(removePatient);
        searchPatient.setBounds(200,450,300,120);
        registerSearchPatient(searchPatient);
        modifyPatient.setBounds(200,650,300,120);
        registerModifyPatient(modifyPatient);
        save.setBounds(200,850,300,120);
        registerSave(save);
    }

    private void registerAddPatient(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number = JOptionPane.showInputDialog("Input care card number");
                try {
                    doctor.addPatient(new Patient(number));
                    playSound("./data/2.wav");
                    JOptionPane.showMessageDialog(frame, "Patient added successfully!");
                } catch (TooManyPatients tooManyPatients) {
                    JOptionPane.showMessageDialog(frame,"Sorry, the patient list is currently full.");
                }
            }
        });
    }

    private void registerRemovePatient(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number = JOptionPane.showInputDialog("Input care card number");
                doctor.removePatient(doctor.searchPatient(number));
                playSound("./data/2.wav");
                JOptionPane.showMessageDialog(frame,"Patient removed Successfully!");
            }
        });
    }

    private void registerSearchPatient(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number = JOptionPane.showInputDialog("Input care card number");
                Patient patient = doctor.searchPatient(number);
                playSound("./data/1.wav");
                JOptionPane.showMessageDialog(frame, patient.toString());
            }
        });
    }

    private void registerModifyPatient(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number = JOptionPane.showInputDialog("Input care card number");
                Patient patient = doctor.searchPatient(number);
                String[]  options = {"Recoverd", "During treatment"};
                int x = JOptionPane.showOptionDialog(frame, "select the patient health status:", "",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                boolean status = x == 0;
                patient.setHealingStatus(status);
                playSound("./data/2.wav");
                JOptionPane.showMessageDialog(frame,"Patient's information has been modified!");
            }
        });
    }

    private void registerSave(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doctor.saveList();
                playSound("./data/1.wav");
                JOptionPane.showMessageDialog(frame,"Information has been saved!");
            }
        });
    }

    private void playSound(String filename) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
}
