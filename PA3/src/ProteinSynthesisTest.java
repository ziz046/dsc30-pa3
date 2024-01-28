import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProteinSynthesisTest {
    String invalidDna;
    String validDna1;
    String validDna2;
    String noStartDna;
    String noStopDna;
    ProteinSynthesis synthesizer;

    @BeforeEach
    void setUp() {
        invalidDna = new String("ATTACGTTAT");
        validDna1 = new String("GCTACTGTA");
        validDna2 = new String("ATCATGCGTTAGACC");
        noStartDna = new String("ATCCGTTAGTAG");
        noStopDna = new String("ATCATGCGTAAGACC");
        synthesizer = new ProteinSynthesis();
    }

    @Test
    void transcribeDNATest() {
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            synthesizer.transcribeDNA(invalidDna);
        });
        CharQueue rna1 = synthesizer.transcribeDNA(validDna1);
        assertEquals(9, rna1.size());
        assertEquals('G', rna1.dequeue());
        rna1.dequeue();
        assertEquals('U', rna1.dequeue());
    }

    @Test
    void translateRNATest() {
        CharQueue rna1 = synthesizer.transcribeDNA(validDna1);
        CharQueue translated1 = synthesizer.translateRNA(rna1);
        assertEquals(0, translated1.size());
        assertTrue(translated1.isEmpty());
        CharQueue rna2 = synthesizer.transcribeDNA(validDna2);
        CharQueue translated2 = synthesizer.translateRNA(rna2);
        assertEquals(3, translated2.size());
        assertEquals('M', translated2.dequeue());
        assertEquals('R', translated2.dequeue());
        assertEquals('*', translated2.dequeue());
        assertTrue(translated2.isEmpty());
        CharQueue rna3 = synthesizer.transcribeDNA(noStartDna);
        CharQueue translated3 = synthesizer.translateRNA(rna3);
        assertEquals(0, translated3.size());
        assertTrue(translated3.isEmpty());
        CharQueue rna4 = synthesizer.transcribeDNA(noStopDna);
        CharQueue translated4 = synthesizer.translateRNA(rna4);
        assertEquals(4, translated4.size());
        assertEquals('M', translated4.dequeue());
        assertEquals('R', translated4.dequeue());
        assertEquals('K', translated4.dequeue());
        assertEquals('T', translated4.dequeue());
    }
}