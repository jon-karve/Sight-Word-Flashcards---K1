package com.baub.customflashcards

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.baub.customflashcards.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
//    val sharedPref = requireActivity().getSharedPreferences("GlobalSettings", 0)
//    val editor = sharedPref.edit()

    var cardNames = arrayOf("can","the","I","we","out","down")
    var cardFrequencies = arrayOf(1,1,1,1,1,1)
    var cardSounds = arrayOf(R.raw.can,R.raw.the,R.raw.i,R.raw.we,R.raw.out,R.raw.down)
    var cardIndex = 0
    var cardCount = 6
    var cardTotal = 6
    var numbers = (0..5).toList().shuffled().toTypedArray()
    var cardList = (0..5).toList().shuffled().toTypedArray()
    var level = 1
    var score = 0

    fun resetCards(){
        cardIndex = 0
        cardCount = 10
        cardFrequencies = arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
        score = 0
        binding.scoreText.text = "0/" + cardTotal.toString()


        if(level == 1) {
            cardNames = arrayOf("I", "can", "see", "the", "we", "a","like", "to", "and", "go")
            cardSounds = arrayOf(R.raw.i, R.raw.can, R.raw.see, R.raw.the, R.raw.we, R.raw.a ,R.raw.like, R.raw.to, R.raw.and, R.raw.go)
        } else if (level == 2){
            cardNames = arrayOf("you", "my", "do", "are", "with", "she","he", "is", "little", "was")
            cardSounds = arrayOf(R.raw.you, R.raw.my, R.raw.do_sound, R.raw.are, R.raw.with, R.raw.she ,R.raw.he, R.raw.is_sound, R.raw.little, R.raw.was)
        } else if (level == 3){
            cardNames = arrayOf("for", "have", "of", "they", "said", "want","here", "me", "this", "what")
            cardSounds = arrayOf(R.raw.for_sound, R.raw.have, R.raw.of, R.raw.they, R.raw.said, R.raw.want ,R.raw.here, R.raw.me, R.raw.this_sound, R.raw.what)
        } else if (level == 4){
            cardNames = arrayOf("help", "too", "has", "play", "where", "look","good", "who", "come", "does")
            cardSounds = arrayOf(R.raw.help, R.raw.too, R.raw.has, R.raw.play, R.raw.where, R.raw.look ,R.raw.good, R.raw.who, R.raw.come, R.raw.does)
        } else if (level == 5){
            cardNames = arrayOf("an", "all", "at", "not", "so") + arrayOf("day", "how", "no", "in", "boy")
            cardSounds = arrayOf(R.raw.an, R.raw.all, R.raw.at, R.raw.not, R.raw.so) + arrayOf(R.raw.day, R.raw.how, R.raw.no_sound, R.raw.in_sound, R.raw.boy)
        } else if (level == 6){
            cardNames = arrayOf("us", "him", "many", "it", "out") + arrayOf("girl", "if", "her", "ask", "car")
            cardSounds = arrayOf(R.raw.us, R.raw.him, R.raw.many, R.raw.it, R.raw.out) + arrayOf(R.raw.girl, R.raw.if_sound, R.raw.her, R.raw.ask, R.raw.car)
        } else if (level == 7){
            cardNames = arrayOf("yes", "will", "two", "on", "as") + arrayOf("by", "one", "from", "his", "had")
            cardSounds = arrayOf(R.raw.yes, R.raw.will, R.raw.two, R.raw.on, R.raw.as_sound) + arrayOf(R.raw.by, R.raw.one, R.raw.from, R.raw.his, R.raw.had)
        } else if (level == 8){
            cardNames = arrayOf("find", "pretty", "make", "that", "or") + arrayOf("long", "fun", "get", "off", "be")
            cardSounds = arrayOf(R.raw.find, R.raw.pretty, R.raw.make, R.raw.that, R.raw.or) + arrayOf(R.raw.long_sound, R.raw.fun_sound, R.raw.get, R.raw.off, R.raw.be)
        } else if (level == 9){
            cardNames = arrayOf("word", "for", "this", "but", "there") + arrayOf("their", "them", "into", "big", "were")
            cardSounds = arrayOf(R.raw.word, R.raw.for_sound, R.raw.this_sound, R.raw.but, R.raw.there) + arrayOf(R.raw.their, R.raw.them, R.raw.into, R.raw.big, R.raw.were)
        } else if (level == 10){
            cardNames = arrayOf("use", "about", "then", "time", "way") + arrayOf("when", "which", "other", "these", "more")
            cardSounds = arrayOf(R.raw.use, R.raw.about, R.raw.then, R.raw.time, R.raw.way) + arrayOf(R.raw.when_sound, R.raw.which, R.raw.other, R.raw.these, R.raw.more)
        } else if (level == 11){
            cardNames = arrayOf("some", "has", "with", "could", "went") + arrayOf("old", "why", "after", "than", "now")
            cardSounds = arrayOf(R.raw.some, R.raw.has, R.raw.with, R.raw.could, R.raw.went) + arrayOf(R.raw.old, R.raw.why, R.raw.after, R.raw.than, R.raw.now)
        } else if (level == 12){
            cardNames = arrayOf("made", "run", "over", "am", "eat") + arrayOf("your", "may", "tell", "give", "ride")
            cardSounds = arrayOf(R.raw.made, R.raw.run, R.raw.over, R.raw.am, R.raw.eat) + arrayOf(R.raw.your, R.raw.may, R.raw.tell, R.raw.give, R.raw.ride)
        } else if (level == 13){
            cardNames = arrayOf("call", "did", "part", "each", "saw") + arrayOf("been", "up", "would", "new", "down")
            cardSounds = arrayOf(R.raw.call, R.raw.did, R.raw.part, R.raw.each, R.raw.saw) + arrayOf(R.raw.been, R.raw.up, R.raw.would, R.raw.new_sound, R.raw.down)
        }

        cardTotal = cardCount;
        val indices = cardNames.indices.toList()

        // Shuffle the list of indices
        val shuffledIndices = indices.shuffled()

        // Reorder both arrays based on the shuffled indices
        cardNames = Array(cardNames.size) { i -> cardNames[shuffledIndices[i]] }
        cardSounds = Array(cardSounds.size) { i -> cardSounds[shuffledIndices[i]] }

        binding.buttonCard.text = cardNames[cardIndex]
    }


    fun removeCard(){
        cardNames = cardNames.toMutableList().apply { removeAt(cardIndex) }.toTypedArray()
        cardSounds = cardSounds.toMutableList().apply { removeAt(cardIndex) }.toTypedArray()
        cardIndex %= cardCount
        binding.buttonCard.text = cardNames[cardIndex]
    }

    fun addCard(){
//        cardFrequencies[cardIndex]++
//        score = 0
//        binding.scoreText.text = score.toString() + "/10"
    }

    fun nextCard(){
        cardIndex = (cardIndex+1)%cardCount
        binding.buttonCard.text = cardNames[cardIndex]
    }

    fun disableButtons(){
        binding.buttonEasy.isEnabled = false
        binding.buttonEasy.isClickable = false
        binding.buttonMedium.isEnabled = false
        binding.buttonMedium.isClickable = false
        binding.buttonHard.isEnabled = false
        binding.buttonHard.isClickable = false
    }

    fun enableButtons(){
        binding.buttonEasy.isEnabled = true
        binding.buttonEasy.isClickable = true
//        binding.buttonMedium.isEnabled = true
//        binding.buttonMedium.isClickable = true
        binding.buttonHard.isEnabled = true
        binding.buttonHard.isClickable = true
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = requireActivity().getSharedPreferences("GlobalSettings", 0)
        level = sharedPref.getInt("setting_testlevel", 1)
        resetCards()
        disableButtons()
        binding.buttonMedium.isVisible = false
        binding.endScoreText.isVisible = false
        binding.scoreText.text = "0/"+cardTotal.toString()


        // Register the MenuProvider
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu) // Inflate your menu
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_settings -> {
                        // Navigate to SettingsFragment
                        findNavController().navigate(R.id.action_SecondFragment_to_thirdFragment6)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED) // Use viewLifecycleOwner to avoid memory leaks

        binding.buttonCard.setOnClickListener {
            playSound(cardSounds[cardIndex])
            enableButtons()
            //findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.buttonEasy.setOnClickListener {
            score++
            binding.scoreText.text = score.toString() + "/" + cardTotal.toString()
            if(cardFrequencies[cardIndex]>1){
                cardFrequencies[cardIndex]--
                nextCard()
            }else {
                cardCount--
                if (cardCount == 0) {
//                    playVictory()
//                    resetCards()
                      endGame()
                } else {
                    removeCard()
                }
            }
            disableButtons()        }

        binding.buttonMedium.setOnClickListener {
            nextCard()
            disableButtons()
            //findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.buttonHard.setOnClickListener {
            //previously this was meant to increase frequency
            //changed this to more of a test mode with no repeats. maybe later create this as a new mode
//                addCard()
//                nextCard()
//                disableButtons()
            if(cardFrequencies[cardIndex]>1){
                cardFrequencies[cardIndex]--
                nextCard()
            }else {
                cardCount--
                if (cardCount == 0) {
//                    playVictory()
//                    resetCards()
                      endGame()
                } else {
                    removeCard()
                }
            }
            disableButtons()
        }

        binding.endScoreText.setOnClickListener {
//            score = 0
//            binding.scoreText.text = score.toString() + "/10"
            binding.endScoreText.isVisible = false
            resetCards()
        }


    }

    private fun endGame(){
        binding.endScoreText.isVisible = true
        binding.endScoreText.text = "Your Score:\n\n" + score.toString() + "/" + cardTotal.toString()
    }

    private fun easyClicked() {
        if(cardFrequencies[cardIndex]>1){
            cardFrequencies[cardIndex]--
            nextCard()
        }else {
            cardCount--
            if (cardCount == 0) {
//                    playVictory()
                resetCards()
            } else {
                removeCard()
            }
        }
        disableButtons()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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