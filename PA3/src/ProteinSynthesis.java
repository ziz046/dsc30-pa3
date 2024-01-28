/*
    Name: Zixun Zhang
    PID:  A16985661
 */

/**
 * DNA Transcription & Translation
 * Utilize the CharQueue to transcribe DNA into RNA, and then translate RNA into Amino Acid.
 *
 * @author Zixun Zhang
 * @since Jan. 26, 2024
 */
class ProteinSynthesis {
    private static final int CODON_SIZE = 3;
    /**
     * Creates an RNA by replacing each occurrence of ‘T’ with ‘U’,
     * saves the result in a queue and returns it.
     *
     * @param dna the DNA sequence needed to transcribe.
     * @return the corresponding RNA sequence.
     * @throws IllegalArgumentException if the length of DNA sequence is not divisible by 3.
     */
    public CharQueue transcribeDNA(String dna) {
        int len = dna.length();
        if (len % CODON_SIZE != 0) {
            throw new IllegalArgumentException("DNA sequence is not valid.");
        }
        char[] dnaCharArr = dna.toCharArray();
        CharQueue rna = new CharQueue(len);
        for (int i = 0; i < len; i++) {
            if (dnaCharArr[i] == 'T') {
                rna.enqueue('U');
            }
            else {
                rna.enqueue(dnaCharArr[i]);
            }
        }
        return rna;
    }

    /**
     * Translates a given RNA to a protein.
     *
     * @param rna the RNA sequence needed to translate.
     * @return the corresponding Amino Acid sequence.
     */
    public CharQueue translateRNA(CharQueue rna) {
        int len = rna.size();
        int codonsLen = len / CODON_SIZE;
        int startIdx = -1;
        int stopIdx = -1;
        String[] codons = new String[codonsLen];
        char[] rawAminoAcid= new char[codonsLen];
        CharQueue aminoAcid = new CharQueue(codonsLen);

        for (int i = 0; i < len - 2; i = i + CODON_SIZE) {
            char[] temp = new char[CODON_SIZE];
            temp[0] = rna.dequeue();
            temp[1] = rna.dequeue();
            temp[2] = rna.dequeue();
            codons[i/CODON_SIZE] = new String(temp);
        }

        for (int j = 0; j < codonsLen; j++) {
            rawAminoAcid[j] = CodonMap.getAminoAcid(codons[j]);
            if (rawAminoAcid[j] == 'M' && stopIdx == -1) {
                startIdx = j;
            }
            if (rawAminoAcid[j] == '*') {
                stopIdx = j;
            }
        }

        if (startIdx != -1 && stopIdx == -1) {
            for (int k = startIdx; k < codonsLen; k++) {
                aminoAcid.enqueue(rawAminoAcid[k]);
            }
        }
        if (startIdx != -1 && stopIdx != -1) {
            for (int l = startIdx; l <= stopIdx; l++) {
                aminoAcid.enqueue(rawAminoAcid[l]);
            }
        }

        return aminoAcid;
    }
}
