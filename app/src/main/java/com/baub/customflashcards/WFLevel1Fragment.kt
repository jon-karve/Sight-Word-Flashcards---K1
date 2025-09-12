package com.baub.customflashcards

import android.annotation.SuppressLint
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
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
import com.baub.customflashcards.databinding.FragmentWFLevel1Binding

class WFLevel1Fragment : Fragment() {
    private var _binding: FragmentWFLevel1Binding? = null


    var score = 0
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var level = 1
    var streak = 0
    var cardNames = arrayOf("can", "the", "I", "we", "out", "down")
    var cardSounds = arrayOf(R.raw.can, R.raw.the, R.raw.i, R.raw.we, R.raw.out, R.raw.down)
    var numbers = (0..5).toList().shuffled().toTypedArray()
    var cardList = (0..5).toList().shuffled().toTypedArray()
    var cardCount = 6


    fun playHint(){
        playSound(cardSounds[cardList[0]])
    }
    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {
        _binding = FragmentWFLevel1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = requireActivity().getSharedPreferences("GlobalSettings", 0)
        streak = sharedPref.getInt("streakNumL1", 0)
        binding.textScore.text = streak.toString()
//        val editor = sharedPref.edit()
        level = sharedPref.getInt("setting_level", 1)
        // Register the MenuProvider
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu) // Inflate your menu
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_settings -> {
                        // Navigate to SettingsFragment
                        findNavController().navigate(R.id.action_thirdFragment2_to_thirdFragment3)
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
            numbers = (0..5).toList().shuffled().toTypedArray()
            cardList = (0..5).toList().shuffled().toTypedArray()
            cardCount = 6

        if(level == 1) {
            cardNames = arrayOf("I", "can", "see", "the", "we", "out")
            cardSounds = arrayOf(R.raw.i, R.raw.can, R.raw.see, R.raw.the, R.raw.we, R.raw.out)
        } else if (level == 2){
            cardNames = arrayOf("a", "like", "to", "and", "go", "will")
            cardSounds = arrayOf(R.raw.a, R.raw.like, R.raw.to, R.raw.and, R.raw.go, R.raw.will)
        } else if (level == 3){
            cardNames = arrayOf("you", "my", "do", "are", "with", "down")
            cardSounds = arrayOf(R.raw.you, R.raw.my, R.raw.do_sound, R.raw.are, R.raw.with, R.raw.down)
        } else if (level == 4){
            cardNames = arrayOf("she", "he", "is", "little", "was", "two")
            cardSounds = arrayOf(R.raw.she, R.raw.he, R.raw.is_sound, R.raw.little, R.raw.was, R.raw.two)
        } else if (level == 5){
            cardNames = arrayOf("for", "have", "of", "they", "said", "when")
            cardSounds = arrayOf(R.raw.for_sound, R.raw.have, R.raw.of, R.raw.they, R.raw.said, R.raw.when_sound)
        } else if (level == 6){
            cardNames = arrayOf("want", "here", "me", "this", "what", "some")
            cardSounds = arrayOf(R.raw.want, R.raw.here, R.raw.me, R.raw.this_sound, R.raw.what, R.raw.some)
        } else if (level == 7){
            cardNames = arrayOf("help", "too", "has", "play", "where", "have")
            cardSounds = arrayOf(R.raw.help, R.raw.too, R.raw.has, R.raw.play, R.raw.where, R.raw.have)
        } else if (level == 8){
            cardNames = arrayOf("look", "good", "who", "come", "does", "want")
            cardSounds = arrayOf(R.raw.look, R.raw.good, R.raw.who, R.raw.come, R.raw.does, R.raw.want)
        } else if (level == 9){
            cardNames = arrayOf("an", "all", "at", "not", "so", "are")
            cardSounds = arrayOf(R.raw.an, R.raw.all, R.raw.at, R.raw.not, R.raw.so, R.raw.are)
        } else if (level == 10){
            cardNames = arrayOf("day", "how", "no", "in", "boy") + arrayOf("does")
            cardSounds = arrayOf(R.raw.day, R.raw.how, R.raw.no_sound, R.raw.in_sound, R.raw.boy) + arrayOf(R.raw.does)
        } else if (level == 11){
            cardNames = arrayOf("us", "him", "many", "it", "out") + arrayOf("day")
            cardSounds = arrayOf(R.raw.us, R.raw.him, R.raw.many, R.raw.it, R.raw.out) + arrayOf(R.raw.day)
        } else if (level == 12){
            cardNames = arrayOf("girl", "if", "her", "ask", "car") + arrayOf("have")
            cardSounds = arrayOf(R.raw.girl, R.raw.if_sound, R.raw.her, R.raw.ask, R.raw.car) + arrayOf(R.raw.have)
        } else if (level == 13){
            cardNames = arrayOf("yes", "will", "two", "on", "as") + arrayOf("girl")
            cardSounds = arrayOf(R.raw.yes, R.raw.will, R.raw.two, R.raw.on, R.raw.as_sound) + arrayOf(R.raw.girl)
        } else if (level == 14){
            cardNames = arrayOf("by", "one", "from", "his", "had") + arrayOf("little")
            cardSounds = arrayOf(R.raw.by, R.raw.one, R.raw.from, R.raw.his, R.raw.had) + arrayOf(R.raw.little)
        } else if (level == 15){
            cardNames = arrayOf("find", "pretty", "make", "that", "or") + arrayOf("from")
            cardSounds = arrayOf(R.raw.find, R.raw.pretty, R.raw.make, R.raw.that, R.raw.or) + arrayOf(R.raw.from)
        } else if (level == 16){
            cardNames = arrayOf("long", "fun", "get", "off", "be") + arrayOf("many")
            cardSounds = arrayOf(R.raw.long_sound, R.raw.fun_sound, R.raw.get, R.raw.off, R.raw.be) + arrayOf(R.raw.many)
        } else if (level == 17){
            cardNames = arrayOf("word", "for", "this", "but", "there") + arrayOf("out")
            cardSounds = arrayOf(R.raw.word, R.raw.for_sound, R.raw.this_sound, R.raw.but, R.raw.there) + arrayOf(R.raw.out)
        } else if (level == 18){
            cardNames = arrayOf("their", "them", "into", "big", "were") + arrayOf("pretty")
            cardSounds = arrayOf(R.raw.their, R.raw.them, R.raw.into, R.raw.big, R.raw.were) + arrayOf(R.raw.pretty)
        } else if (level == 19){
            cardNames = arrayOf("use", "about", "then", "time", "way") + arrayOf("make")
            cardSounds = arrayOf(R.raw.use, R.raw.about, R.raw.then, R.raw.time, R.raw.way) + arrayOf(R.raw.make)
        } else if (level == 20){
            cardNames = arrayOf("when", "which", "other", "these", "more") + arrayOf("their")
            cardSounds = arrayOf(R.raw.when_sound, R.raw.which, R.raw.other, R.raw.these, R.raw.more) + arrayOf(R.raw.their)
        } else if (level == 21){
            cardNames = arrayOf("some", "has", "with", "could", "went") + arrayOf("other")
            cardSounds = arrayOf(R.raw.some, R.raw.has, R.raw.with, R.raw.could, R.raw.went) + arrayOf(R.raw.other)
        } else if (level == 22){
            cardNames = arrayOf("old", "why", "after", "than", "now") + arrayOf("which")
            cardSounds = arrayOf(R.raw.old, R.raw.why, R.raw.after, R.raw.than, R.raw.now) + arrayOf(R.raw.which)
        } else if (level == 23){
            cardNames = arrayOf("made", "run", "over", "am", "eat") + arrayOf("when")
            cardSounds = arrayOf(R.raw.made, R.raw.run, R.raw.over, R.raw.am, R.raw.eat) + arrayOf(R.raw.when_sound)
        } else if (level == 24){
            cardNames = arrayOf("your", "may", "tell", "give", "ride") + arrayOf("were")
            cardSounds = arrayOf(R.raw.your, R.raw.may, R.raw.tell, R.raw.give, R.raw.ride) + arrayOf(R.raw.were)
        } else if (level == 25){
            cardNames = arrayOf("call", "did", "part", "each", "saw") + arrayOf("over")
            cardSounds = arrayOf(R.raw.call, R.raw.did, R.raw.part, R.raw.each, R.raw.saw) + arrayOf(R.raw.over)
        } else if (level == 26){
            cardNames = arrayOf("been", "up", "would", "new", "down") + arrayOf("run")
            cardSounds = arrayOf(R.raw.been, R.raw.up, R.raw.would, R.raw.new_sound, R.raw.down) + arrayOf(R.raw.run)
        }

        setPosition(binding.buttonFind1,1)
        setPosition(binding.buttonFind2,2)
        setPosition(binding.buttonFind3,3)
        setPosition(binding.buttonFind4,4)
        setPosition(binding.buttonFind5,5)
        setPosition(binding.buttonFind6,6)
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
        editor.putInt("streakNumL1", streak)
        editor.apply()
        binding.textScore.text = streak.toString()
    }

    private fun endStreak() {
        streak = 0
        val sharedPref = requireActivity().getSharedPreferences("GlobalSettings", 0)
        val editor = sharedPref.edit()
        editor.putInt("streakNumL1", streak)
        editor.apply()
        binding.textScore.text = streak.toString()
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

    }

    @SuppressLint("DiscouragedApi")
    fun playSound(resid: Int){
        val resId = resources.getIdentifier(resid.toString(),
            "raw", activity?.packageName)

        val mediaPlayer = MediaPlayer.create(activity, resId)
        mediaPlayer.start()

        mediaPlayer.setOnCompletionListener{
            it.release()
        }
    }
}