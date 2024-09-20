package com.baub.customflashcards

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.baub.customflashcards.databinding.FragmentWFLevel1Binding
import kotlin.random.Random

class WFLevel1Fragment : Fragment() {
    private var _binding: FragmentWFLevel1Binding? = null

    var score = 0
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var level = 1
    var cardNames = arrayOf("can", "the", "I", "we", "out", "down")
    var cardSounds = arrayOf(R.raw.can, R.raw.the, R.raw.i, R.raw.we, R.raw.out, R.raw.down)
    var numbers = (0..5).toList().shuffled().toTypedArray()
    var cardList = (0..5).toList().shuffled().toTypedArray()
    var cardCount = 6

    fun playHint(){
        playSound(cardSounds[cardList[0]])
    }
    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentWFLevel1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resetCards()
        binding.buttonHint.setOnClickListener{
            playHint()
        }
//        binding.buttonFind1.setOnClickListener { playSound(R.raw.can) }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun resetCards(){
            numbers = (0..5).toList().shuffled().toTypedArray()
            cardList = (0..5).toList().shuffled().toTypedArray()
//        button.visibility = View.INVISIBLE  // or View.GONE if you want it to disappear entirely
//        button.isClickable = false
        if(level == 1) {
            cardNames = arrayOf("can", "the", "I", "we", "out", "down")
            cardSounds = arrayOf(R.raw.can, R.raw.the, R.raw.i, R.raw.we, R.raw.out, R.raw.down)
            cardCount = 6
            binding.buttonFind1.text = cardNames[numbers[0]]
            binding.buttonFind1.setOnClickListener {
                playSound(cardSounds[numbers[0]])
                println("numbers[0]:" + numbers[0])
                println("cardList[0]:" + cardList[0])
                if(cardList[0] == numbers[0]){
                    //choice is correct - disappear the card and decrement the card count
                    cardCount--
                    binding.buttonFind1.visibility = View.INVISIBLE
                    binding.buttonFind1.isClickable = false
                    if(cardCount > 0) {
                        cardList = cardList.toMutableList().apply {removeAt(0)}.toTypedArray()
                    } else {
                        resetCards()
                    }
                }
            }

            binding.buttonFind2.text = cardNames[numbers[1]]
            binding.buttonFind2.setOnClickListener {
                playSound(cardSounds[numbers[1]]) //playSound(cardSounds[cardList[0]])
                println("numbers[1]:" + numbers[1])
                println("cardList[0]:" + cardList[0])
                if(cardList[0] == numbers[1]){
                    //choice is correct - disappear the card and decrement the card count
                    cardCount--
                    binding.buttonFind2.visibility = View.INVISIBLE
                    binding.buttonFind2.isClickable = false
                    if(cardCount > 0) {
                        cardList = cardList.toMutableList().apply {removeAt(0)}.toTypedArray()
                    } else {
                        resetCards()
                    }
                }
            }
            binding.buttonFind3.text = cardNames[numbers[2]]
            binding.buttonFind3.setOnClickListener {
                playSound(cardSounds[numbers[2]])
                println("numbers[2]:" + numbers[2])
                println("cardList[0]:" + cardList[0])
                if(cardList[0] == numbers[2]){
                    //choice is correct - disappear the card and decrement the card count
                    cardCount--
                    binding.buttonFind3.visibility = View.INVISIBLE
                    binding.buttonFind3.isClickable = false
                    if(cardCount > 0) {
                        cardList = cardList.toMutableList().apply {removeAt(0)}.toTypedArray()
                    } else {
                        resetCards()
                    }
                }
            }
            binding.buttonFind4.text = cardNames[numbers[3]]
            binding.buttonFind4.setOnClickListener {
                playSound(cardSounds[numbers[3]])
                println("numbers[3]:" + numbers[3])
                println("cardList[0]:" + cardList[0])
                if(cardList[0] == numbers[3]){
                    //choice is correct - disappear the card and decrement the card count
                    cardCount--
                    binding.buttonFind4.visibility = View.INVISIBLE
                    binding.buttonFind4.isClickable = false
                    if(cardCount > 0) {
                        cardList = cardList.toMutableList().apply {removeAt(0)}.toTypedArray()
                    } else {
                        resetCards()
                    }
                }
            }
            binding.buttonFind5.text = cardNames[numbers[4]]
            binding.buttonFind5.setOnClickListener {
                playSound(cardSounds[numbers[4]])
                println("numbers[4]:" + numbers[4])
                println("cardList[0]:" + cardList[0])
                if(cardList[0] == numbers[4]){
                    //choice is correct - disappear the card and decrement the card count
                    cardCount--
                    binding.buttonFind5.visibility = View.INVISIBLE
                    binding.buttonFind5.isClickable = false
                    if(cardCount > 0) {
                        cardList = cardList.toMutableList().apply {removeAt(0)}.toTypedArray()
                    } else {
                        resetCards()
                    }
                }
            }
            binding.buttonFind6.text = cardNames[numbers[5]]
            binding.buttonFind6.setOnClickListener {
                playSound(cardSounds[numbers[5]])
                println("numbers[5]:" + numbers[5])
                println("cardList[0]:" + cardList[0])
                if(cardList[0] == numbers[5]){
                    println("Card List [0]:"+cardList[0])
                    //choice is correct - disappear the card and decrement the card count
                    cardCount--
                    binding.buttonFind6.visibility = View.INVISIBLE
                    binding.buttonFind6.isClickable = false
                    if(cardCount > 0) {
                        cardList = cardList.toMutableList().apply {removeAt(0)}.toTypedArray()
                    } else {
                        resetCards()
                    }
                }
            }
        }//end if level 1
    }

    @SuppressLint("DiscouragedApi")
    fun playSound(resid: Int){
        val resId = getResources().getIdentifier(resid.toString(),
            "raw", activity?.packageName)

        val mediaPlayer = MediaPlayer.create(activity, resId)
        mediaPlayer.start()

        mediaPlayer.setOnCompletionListener{
            it.release()
        }
    }
}