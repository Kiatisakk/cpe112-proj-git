package ui;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private Clip clip;
    // method เล่นเพลง
    public void playMusic(String filepath) {
        try {
            File musicPath = new File(filepath); // เรียกไฟล์
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath); // เรียก audio จาก musicpath
                clip = AudioSystem.getClip(); // getclop
                clip.open(audioInput); // set open เพลง
                clip.loop(Clip.LOOP_CONTINUOUSLY); // loop
                clip.start(); // เริ่มเพลง
            } else {
                System.out.println("File not found: " + filepath);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    // method หยุดเพลง
    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public boolean isPlaying() { // เช็คว่าเล่นอยู่มั้ย
        return clip != null && clip.isRunning();
    }
}
