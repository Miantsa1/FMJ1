package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class JudoScoreBoard extends JFrame {
    private JButton combattant1Score1;
    private JButton combattant1Score2;
    private JButton combattant2Score1;
    private JButton combattant2Score2;
    private JButton timerButton;
    private Timer timer;
    private int minutes = 4;
    private int seconds = 0;
    private boolean timerRunning = false;

    private final Color BLUE_BACKGROUND = new Color(0, 51, 102); 
    private final Color BLUE_BACKGROUND1 = new Color(0, 51, 70);
    private final Color GRAY_BACKGROUND = new Color(220, 220, 220);
    private final Color BLACK_BACKGROUND = new Color(0, 0, 0);
    private final Color WHITE_TEXT = new Color(255, 255, 255);
    private final Color BLACK_TEXT = new Color(0, 0, 0);
    private final Color RED_IPPON = new Color(255, 0, 0);

    public JudoScoreBoard(String club1, String nomPrenom1, String club2, String nomPrenom2, String typeCombat, String categorieAge, String categoriePoids) {
        setTitle("Score board JUDO1");
        setSize(1500, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(GRAY_BACKGROUND);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
        mainPanel.setBackground(BLUE_BACKGROUND);

        JLabel titleLabel = new JLabel("Score board JUDO", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(WHITE_TEXT);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        JLabel subtitleLabel = new JLabel("Monté sur tatami", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.ITALIC, 24));
        subtitleLabel.setForeground(WHITE_TEXT);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(subtitleLabel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        JPanel competitorsPanel = new JPanel();
        competitorsPanel.setLayout(new BoxLayout(competitorsPanel, BoxLayout.Y_AXIS));
        competitorsPanel.setBackground(BLUE_BACKGROUND1);
        competitorsPanel.setBorder(BorderFactory.createLineBorder(BLACK_BACKGROUND, 2));

        JPanel combattant1 = createCompetitorPanel(club1, nomPrenom1);
        competitorsPanel.add(combattant1);
        competitorsPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel competitorsPanel2 = new JPanel();
        competitorsPanel2.setLayout(new BoxLayout(competitorsPanel2, BoxLayout.Y_AXIS));
        competitorsPanel2.setBackground(WHITE_TEXT);
        competitorsPanel2.setBorder(BorderFactory.createLineBorder(BLUE_BACKGROUND, 2));

        JPanel combattant2 = createCompetitorPanel(club2, nomPrenom2);
        competitorsPanel2.add(combattant2);
        competitorsPanel2.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel matchInfoPanel = new JPanel(new BorderLayout());
        matchInfoPanel.setBackground(BLACK_BACKGROUND);
        matchInfoPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JPanel matchTypePanel = new JPanel();
        matchTypePanel.setLayout(new BoxLayout(matchTypePanel, BoxLayout.Y_AXIS));
        matchTypePanel.setBackground(BLACK_BACKGROUND);
        
        JLabel matchTypeLabel = new JLabel(typeCombat);
        matchTypeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        matchTypeLabel.setForeground(WHITE_TEXT);
        
        JLabel categoryLabel = new JLabel(categorieAge + " " + categoriePoids + "kg");
        categoryLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        categoryLabel.setForeground(WHITE_TEXT);
        
        matchTypePanel.add(matchTypeLabel);
        matchTypePanel.add(categoryLabel);

        timerButton = new JButton("04:00");
        timerButton.setFont(new Font("Arial", Font.BOLD, 36));
        timerButton.setPreferredSize(new Dimension(150, 70));
        timerButton.setBackground(GRAY_BACKGROUND);
        timerButton.setForeground(BLACK_TEXT);
        timerButton.setBorder(BorderFactory.createLineBorder(WHITE_TEXT, 2));
        timerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleTimer();
            }
        });
        matchInfoPanel.add(matchTypePanel, BorderLayout.WEST);
        matchInfoPanel.add(timerButton, BorderLayout.EAST);
        
        competitorsPanel2.add(matchInfoPanel);
        
        mainPanel.add(competitorsPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(competitorsPanel2);

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createCompetitorPanel(String club, String name) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(GRAY_BACKGROUND);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
    
        JLabel clubLabel = new JLabel(club);
        clubLabel.setFont(new Font("Arial", Font.BOLD, 28));
        clubLabel.setForeground(BLACK_TEXT);
        clubLabel.setPreferredSize(new Dimension(100, 40));
    
        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        nameLabel.setForeground(BLACK_TEXT);
    
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        scorePanel.setBackground(GRAY_BACKGROUND);
        
        JButton score1 = createScoreButton();
        JButton score2 = createScoreButton();
        
        if (combattant1Score1 == null) { // Si c'est le premier combattant créé
            combattant1Score1 = score1;
            combattant1Score2 = score2;
        } else { // Sinon c'est le deuxième combattant
            combattant2Score1 = score1;
            combattant2Score2 = score2;
        }
        
        scorePanel.add(score1);
        scorePanel.add(score2);
    
        panel.add(clubLabel, BorderLayout.WEST);
        panel.add(nameLabel, BorderLayout.CENTER);
        panel.add(scorePanel, BorderLayout.EAST);
    
        return panel;
    }

    private JButton createScoreButton() {
        JButton button = new JButton("0");
        button.setFont(new Font("Arial", Font.BOLD, 28));
        button.setPreferredSize(new Dimension(80, 60));
        button.setBackground(WHITE_TEXT);
        button.setForeground(BLACK_TEXT);
        button.setBorder(BorderFactory.createLineBorder(BLACK_BACKGROUND, 1));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                int currentValue = Integer.parseInt(source.getText());
                source.setText(String.valueOf(currentValue + 1));

                if (source == combattant1Score1 && currentValue + 1 >= 2) {
                    setIppon(combattant1Score1, combattant1Score2);
                } else if (source == combattant2Score1 && currentValue + 1 >= 2) {
                    setIppon(combattant2Score1, combattant2Score2);
                }
            }
        });
        return button;
    }

    private void setIppon(JButton score1, JButton score2) {
        score1.setText("IPPON");
        score1.setFont(new Font("Arial", Font.BOLD, 24));
        score1.setForeground(RED_IPPON);
        score1.setBackground(new Color(255, 240, 240));
        score1.setEnabled(false);
        
        score2.setText("IPPON");
        score2.setFont(new Font("Arial", Font.BOLD, 24));
        score2.setForeground(RED_IPPON);
        score2.setBackground(new Color(255, 240, 240));
        score2.setEnabled(false);
        
        stopTimer();
    }

    private void toggleTimer() {
        if (timerRunning) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    private void startTimer() {
        timerRunning = true;
        timerButton.setForeground(RED_IPPON);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    if (seconds == 0) {
                        if (minutes == 0) {
                            stopTimer();
                            return;
                        }
                        minutes--;
                        seconds = 59;
                    } else {
                        seconds--;
                    }
                    updateTimerDisplay();
                });
            }
        }, 1000, 1000);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
        timerRunning = false;
        timerButton.setForeground(BLACK_TEXT);
    }

    private void updateTimerDisplay() {
        String time = String.format("%02d:%02d", minutes, seconds);
        timerButton.setText(time);
    }

}