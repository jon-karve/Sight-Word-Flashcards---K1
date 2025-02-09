package com.baub.customflashcards

import android.annotation.SuppressLint
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.baub.customflashcards.databinding.FragmentWFLevel2Binding
import kotlin.random.Random

class WFLevel2Fragment : Fragment() {
    private var _binding: FragmentWFLevel2Binding? = null


    var score = 0
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var level = 1
    var streak = 0
    var testLevel = 1;
    var cardNames = arrayOf("can", "the", "I", "we", "out", "down","see", "a", "am", "there")
    var cardSounds = arrayOf(R.raw.can, R.raw.the, R.raw.i, R.raw.we, R.raw.out, R.raw.down,R.raw.see, R.raw.a, R.raw.am, R.raw.there)
    var numbers = (0..9).toList().shuffled().toTypedArray()
    var cardList = (0..9).toList().shuffled().toTypedArray()
    var cardCount = 10




    fun playHint(){
        playSound(cardSounds[cardList[0]])
    }
    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentWFLevel2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = requireActivity().getSharedPreferences("GlobalSettings", 0)
        streak = sharedPref.getInt("streakNumL2", 0)
        binding.textScore.setText(streak.toString())
//        val editor = sharedPref.edit()
        level = sharedPref.getInt("setting_level", 1)
        testLevel = sharedPref.getInt("setting_testlevel", 1)
        // Register the MenuProvider
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu) // Inflate your menu
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_settings -> {
                        // Navigate to SettingsFragment
                        findNavController().navigate(R.id.action_WFL2_to_settings2)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED) // Use viewLifecycleOwner to avoid memory leaks

        resetCards()
        binding.buttonHint.setOnClickListener {
            playHint()
            setIsClickable(true)
        }
//        binding.buttonFind1.setOnClickListener { playSound(R.raw.can) }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun resetCards(){
        numbers = (0..9).toList().shuffled().toTypedArray()
        cardList = (0..9).toList().shuffled().toTypedArray()
        cardCount = 10

        if(testLevel == 1) {
            cardNames = arrayOf("I", "can", "see", "the", "we", "a","like", "to", "and", "go")
            cardSounds = arrayOf(R.raw.i, R.raw.can, R.raw.see, R.raw.the, R.raw.we, R.raw.a ,R.raw.like, R.raw.to, R.raw.and, R.raw.go)
        }
        else if (testLevel == 2){
            cardNames = arrayOf("you", "my", "do", "are", "with", "she","he", "is", "little", "was")
            cardSounds = arrayOf(R.raw.you, R.raw.my, R.raw.do_sound, R.raw.are, R.raw.with, R.raw.she ,R.raw.he, R.raw.is_sound, R.raw.little, R.raw.was)
        }
        else if (testLevel == 3){
            cardNames = arrayOf("for", "have", "of", "they", "said", "want","here", "me", "this", "what")
            cardSounds = arrayOf(R.raw.for_sound, R.raw.have, R.raw.of, R.raw.they, R.raw.said, R.raw.want ,R.raw.here, R.raw.me, R.raw.this_sound, R.raw.what)
        }
        else if (testLevel == 4){
            cardNames = arrayOf("help", "too", "has", "play", "where", "look","good", "who", "come", "does")
            cardSounds = arrayOf(R.raw.help, R.raw.too, R.raw.has, R.raw.play, R.raw.where, R.raw.look ,R.raw.good, R.raw.who, R.raw.come, R.raw.does)
        }
        else if (testLevel == 5){
            cardNames = arrayOf("an", "all", "at", "not", "so") + arrayOf("day", "how", "no", "in", "boy")
            cardSounds = arrayOf(R.raw.an, R.raw.all, R.raw.at, R.raw.not, R.raw.so) + arrayOf(R.raw.day, R.raw.how, R.raw.no_sound, R.raw.in_sound, R.raw.boy)
        }
        else if (testLevel == 6){
            cardNames = arrayOf("us", "him", "many", "it", "out") + arrayOf("girl", "if", "her", "ask", "car")
            cardSounds = arrayOf(R.raw.us, R.raw.him, R.raw.many, R.raw.it, R.raw.out) + arrayOf(R.raw.girl, R.raw.if_sound, R.raw.her, R.raw.ask, R.raw.car)
        }
        else if (testLevel == 7){
            cardNames = arrayOf("yes", "will", "two", "on", "as") + arrayOf("by", "one", "from", "his", "had")
            cardSounds = arrayOf(R.raw.yes, R.raw.will, R.raw.two, R.raw.on, R.raw.as_sound) + arrayOf(R.raw.by, R.raw.one, R.raw.from, R.raw.his, R.raw.had)
        }
        else if (testLevel == 8){
            cardNames = arrayOf("find", "pretty", "make", "that", "or") + arrayOf("long", "fun", "get", "off", "be")
            cardSounds = arrayOf(R.raw.find, R.raw.pretty, R.raw.make, R.raw.that, R.raw.or) + arrayOf(R.raw.long_sound, R.raw.fun_sound, R.raw.get, R.raw.off, R.raw.be)
        }
        else if (testLevel == 9){
            cardNames = arrayOf("word", "for", "this", "but", "there") + arrayOf("their", "them", "into", "big", "were")
            cardSounds = arrayOf(R.raw.word, R.raw.for_sound, R.raw.this_sound, R.raw.but, R.raw.there) + arrayOf(R.raw.their, R.raw.them, R.raw.into, R.raw.big, R.raw.were)
        }
        else if (testLevel == 10){
            cardNames = arrayOf("use", "about", "then", "time", "way") + arrayOf("when", "which", "other", "these", "more")
            cardSounds = arrayOf(R.raw.use, R.raw.about, R.raw.then, R.raw.time, R.raw.way) + arrayOf(R.raw.when_sound, R.raw.which, R.raw.other, R.raw.these, R.raw.more)
        }
        else if (testLevel == 11){
            cardNames = arrayOf("some", "has", "with", "could", "went") + arrayOf("old", "why", "after", "than", "now")
            cardSounds = arrayOf(R.raw.some, R.raw.has, R.raw.with, R.raw.could, R.raw.went) + arrayOf(R.raw.old, R.raw.why, R.raw.after, R.raw.than, R.raw.now)
        }
        else if (testLevel == 12){
            cardNames = arrayOf("made", "run", "over", "am", "eat") + arrayOf("your", "may", "tell", "give", "ride")
            cardSounds = arrayOf(R.raw.made, R.raw.run, R.raw.over, R.raw.am, R.raw.eat) + arrayOf(R.raw.your, R.raw.may, R.raw.tell, R.raw.give, R.raw.ride)
        }
        else if (testLevel == 13){
            cardNames = arrayOf("call", "did", "part", "each", "saw") + arrayOf("been", "up", "would", "new", "down")
            cardSounds = arrayOf(R.raw.call, R.raw.did, R.raw.part, R.raw.each, R.raw.saw) + arrayOf(R.raw.been, R.raw.up, R.raw.would, R.raw.new_sound, R.raw.down)
        }
        setPosition(binding.buttonFind1,1)
        setPosition(binding.buttonFind2,2)
        setPosition(binding.buttonFind3,3)
        setPosition(binding.buttonFind4,4)
        setPosition(binding.buttonFind5,5)
        setPosition(binding.buttonFind6,6)
        setPosition(binding.buttonFind7,7)
        setPosition(binding.buttonFind8,8)
        setPosition(binding.buttonFind9,9)
        setPosition(binding.buttonFind10,10)
        setIsClickable(false)
    }

    fun setPosition(b: Button, pos: Int ){
        b.visibility = View.VISIBLE  // or View.GONE if you want it to disappear entirely
        b.isClickable = true
        b.text = cardNames[numbers[pos-1]]
        b.setOnClickListener {
            playSound(cardSounds[numbers[pos-1]])
            println("numbers[5]:" + numbers[pos-1])
            println("cardList[0]:" + cardList[0])
            println("WHATDOWEHAVE:"+cardNames[cardList[0]])
            val soundsSame = ((b.text == "to" && cardNames[cardList[0]] == "two") || (b.text == "two" && cardNames[cardList[0]] == "to")) ||
                    (b.text == "too" && cardNames[cardList[0]] == "two") || (b.text == "two" && cardNames[cardList[0]] == "too") ||
                    (b.text == "to" && cardNames[cardList[0]] == "too") || (b.text == "too" && cardNames[cardList[0]] == "to")
//            if(cardList[0] == numbers[pos-1] || soundsSame){
            if(b.text == cardNames[cardList[0]] || soundsSame){
                //increase streak
                increaseStreak()
                //choice is correct - disappear the card and decrement the card count
                cardCount--
                b.visibility = View.INVISIBLE
                b.isClickable = false
                if(cardCount > 0) {
                    cardList = cardList.toMutableList().apply {removeAt(0)}.toTypedArray()
                    setIsClickable(false)
                } else {
                    resetCards()
                }
            } else {
                //reset streak
                endStreak()
            }
        }
    }

    private fun increaseStreak() {
        streak++
        val sharedPref = requireActivity().getSharedPreferences("GlobalSettings", 0)
        val editor = sharedPref.edit()
        editor.putInt("streakNumL2", streak)
        editor.apply()
        binding.textScore.setText(streak.toString())
    }

    private fun endStreak() {
        streak = 0
        val sharedPref = requireActivity().getSharedPreferences("GlobalSettings", 0)
        val editor = sharedPref.edit()
        editor.putInt("streakNumL2", streak)
        editor.apply()
        binding.textScore.setText(streak.toString())
    }

    fun setIsClickable(canClick: Boolean){
        binding.buttonFind1.isClickable = canClick
        binding.buttonFind1.setTextColor(if (canClick) Color.WHITE else Color.GRAY)
        binding.buttonFind2.isClickable = canClick
        binding.buttonFind2.setTextColor(if (canClick) Color.WHITE else Color.GRAY)
        binding.buttonFind3.isClickable = canClick
        binding.buttonFind3.setTextColor(if (canClick) Color.WHITE else Color.GRAY)
        binding.buttonFind4.isClickable = canClick
        binding.buttonFind4.setTextColor(if (canClick) Color.WHITE else Color.GRAY)
        binding.buttonFind5.isClickable = canClick
        binding.buttonFind5.setTextColor(if (canClick) Color.WHITE else Color.GRAY)
        binding.buttonFind6.isClickable = canClick
        binding.buttonFind6.setTextColor(if (canClick) Color.WHITE else Color.GRAY)
        binding.buttonFind7.isClickable = canClick
        binding.buttonFind7.setTextColor(if (canClick) Color.WHITE else Color.GRAY)
        binding.buttonFind8.isClickable = canClick
        binding.buttonFind8.setTextColor(if (canClick) Color.WHITE else Color.GRAY)
        binding.buttonFind9.isClickable = canClick
        binding.buttonFind9.setTextColor(if (canClick) Color.WHITE else Color.GRAY)
        binding.buttonFind10.isClickable = canClick
        binding.buttonFind10.setTextColor(if (canClick) Color.WHITE else Color.GRAY)
    }

    override fun onResume() {
        super.onResume()
        val sharedPref = requireActivity().getSharedPreferences("GlobalSettings", 0)
        streak = sharedPref.getInt("streakNumL2", 0)
        binding.textScore.setText(streak.toString())
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