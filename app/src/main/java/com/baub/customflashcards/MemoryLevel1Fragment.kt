package com.baub.customflashcards

import android.annotation.SuppressLint
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
import com.baub.customflashcards.databinding.FragmentMemoryLevel1Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MemoryLevel1Fragment : Fragment() {

    private var _binding: FragmentMemoryLevel1Binding? = null
    private val binding get() = _binding!!

    var level = 1
    var cardNames = arrayOf("can", "the", "I", "we", "out", "down") + arrayOf("can", "the", "I", "we", "out", "down")
    var cardSounds = arrayOf(R.raw.can, R.raw.the, R.raw.i, R.raw.we, R.raw.out, R.raw.down) + arrayOf(R.raw.can, R.raw.the, R.raw.i, R.raw.we, R.raw.out, R.raw.down)
    var numbers = (0..5).toList().shuffled().toTypedArray()
//    var cardList = (0..5).toList().shuffled().toTypedArray()
    var cardCount = 12
    var buttonPosClicked = 0
    var buttonClickedID = 0
    var secondClicked = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentMemoryLevel1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = requireActivity().getSharedPreferences("GlobalSettings", 0)
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
                        findNavController().navigate(R.id.action_memoryLevel1Fragment_to_Menu)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED) // Use viewLifecycleOwner to avoid memory leaks

        resetCards()

//        binding.buttonFind1.setOnClickListener { playSound(R.raw.can) }
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
    fun resetCards(){
        numbers = (0..11).toList().shuffled().toTypedArray()
//        cardList = (0..11).toList().shuffled().toTypedArray()
        cardCount = 12
        buttonPosClicked = 0

        if(level == 1) {
            cardNames = arrayOf("can", "the", "I", "we", "out", "down") + arrayOf("can", "the", "I", "we", "out", "down")
            cardSounds = arrayOf(R.raw.can, R.raw.the, R.raw.i, R.raw.we, R.raw.out, R.raw.down) + arrayOf(R.raw.can, R.raw.the, R.raw.i, R.raw.we, R.raw.out, R.raw.down)
        } else if (level == 2){
            cardNames = arrayOf("see", "a", "am", "there", "will", "like") + arrayOf("see", "a", "am", "there", "will", "like")
            cardSounds = arrayOf(R.raw.see, R.raw.a, R.raw.am, R.raw.there, R.raw.will, R.raw.like) + arrayOf(R.raw.see, R.raw.a, R.raw.am, R.raw.there, R.raw.will, R.raw.like)
        } else if (level == 3){
            cardNames = arrayOf("two", "to", "her", "and", "then", "go") + arrayOf("two", "to", "her", "and", "then", "go")
            cardSounds = arrayOf(R.raw.two, R.raw.to, R.raw.her, R.raw.and, R.raw.then, R.raw.go) + arrayOf(R.raw.two, R.raw.to, R.raw.her, R.raw.and, R.raw.then, R.raw.go)
        } else if (level == 4){
            cardNames = arrayOf("could", "you", "all", "do", "day", "my") + arrayOf("could", "you", "all", "do", "day", "my")
            cardSounds = arrayOf(R.raw.could, R.raw.you, R.raw.all, R.raw.do_sound, R.raw.day, R.raw.my) + arrayOf(R.raw.could, R.raw.you, R.raw.all, R.raw.do_sound, R.raw.day, R.raw.my)
        } else if (level == 5){
            cardNames = arrayOf("than", "his", "are", "when", "which", "with") + arrayOf("than", "his", "are", "when", "which", "with")
            cardSounds = arrayOf(R.raw.than, R.raw.his, R.raw.are, R.raw.when_sound, R.raw.which, R.raw.with) + arrayOf(R.raw.than, R.raw.his, R.raw.are, R.raw.when_sound, R.raw.which, R.raw.with)
        } else if (level == 6){
            cardNames = arrayOf("he", "many", "them", "is", "little", "some") + arrayOf("he", "many", "them", "is", "little", "some")
            cardSounds = arrayOf(R.raw.he, R.raw.many, R.raw.them, R.raw.is_sound, R.raw.little, R.raw.some) + arrayOf(R.raw.he, R.raw.many, R.raw.them, R.raw.is_sound, R.raw.little, R.raw.some)
        } else if (level == 7){
            cardNames = arrayOf("she", "was", "now", "way", "for", "have") + arrayOf("she", "was", "now", "way", "for", "have")
            cardSounds = arrayOf(R.raw.she, R.raw.was, R.raw.now, R.raw.way, R.raw.for_sound, R.raw.have) + arrayOf(R.raw.she, R.raw.was, R.raw.now, R.raw.way, R.raw.for_sound, R.raw.have)
        } else if (level == 8){
            cardNames = arrayOf("from", "how", "of", "they", "water", "these") + arrayOf("from", "how", "of", "they", "water", "these")
            cardSounds = arrayOf(R.raw.from, R.raw.how, R.raw.of, R.raw.they, R.raw.water, R.raw.these) + arrayOf(R.raw.from, R.raw.how, R.raw.of, R.raw.they, R.raw.water, R.raw.these)
        } else if (level == 9){
            cardNames = arrayOf("said", "want", "people", "work", "here", "me") + arrayOf("said", "want", "people", "work", "here", "me")
            cardSounds = arrayOf(R.raw.said, R.raw.want, R.raw.people, R.raw.work, R.raw.here, R.raw.me) + arrayOf(R.raw.said, R.raw.want, R.raw.people, R.raw.work, R.raw.here, R.raw.me)
        } else if (level == 10){
            cardNames = arrayOf("about", "may", "this", "what", "or", "each") + arrayOf("about", "may", "this", "what", "or", "each")
            cardSounds = arrayOf(R.raw.about, R.raw.may, R.raw.this_sound, R.raw.what, R.raw.or, R.raw.each) + arrayOf(R.raw.about, R.raw.may, R.raw.this_sound, R.raw.what, R.raw.or, R.raw.each)
        } else if (level == 11){
            cardNames = arrayOf("help", "too", "other", "into", "more", "by") + arrayOf("help", "too", "other", "into", "more", "by")
            cardSounds = arrayOf(R.raw.help, R.raw.too, R.raw.other, R.raw.into, R.raw.more, R.raw.by) + arrayOf(R.raw.help, R.raw.too, R.raw.other, R.raw.into, R.raw.more, R.raw.by)
        } else if (level == 12){
            cardNames = arrayOf("has", "play", "find", "over", "were", "look") + arrayOf("has", "play", "find", "over", "were", "look")
            cardSounds = arrayOf(R.raw.has, R.raw.play, R.raw.find, R.raw.over, R.raw.were, R.raw.look) + arrayOf(R.raw.has, R.raw.play, R.raw.find, R.raw.over, R.raw.were, R.raw.look)
        } else if (level == 13){
            cardNames = arrayOf("where", "know", "would", "write", "good", "who") + arrayOf("where", "know", "would", "write", "good", "who")
            cardSounds = arrayOf(R.raw.where, R.raw.know, R.raw.would, R.raw.write, R.raw.good, R.raw.who) + arrayOf(R.raw.where, R.raw.know, R.raw.would, R.raw.write, R.raw.good, R.raw.who)
        } else if (level == 14){
            cardNames = arrayOf("part", "only", "words", "come", "does", "first") + arrayOf("part", "only", "words", "come", "does", "first")
            cardSounds = arrayOf(R.raw.part, R.raw.only, R.raw.words, R.raw.come, R.raw.does, R.raw.first) + arrayOf(R.raw.part, R.raw.only, R.raw.words, R.raw.come, R.raw.does, R.raw.first)
        } else if (level == 15){
            cardNames = arrayOf("sound", "their", "as", "be", "one", "three") + arrayOf("sound", "their", "as", "be", "one", "three")
            cardSounds = arrayOf(R.raw.sound, R.raw.their, R.raw.`as`, R.raw.be, R.raw.one, R.raw.three) + arrayOf(R.raw.sound, R.raw.their, R.raw.`as`, R.raw.be, R.raw.one, R.raw.three)
        }
        setPosition(binding.matchButton1,1)
        setPosition(binding.matchButton2,2)
        setPosition(binding.matchButton3,3)
        setPosition(binding.matchButton4,4)
        setPosition(binding.matchButton5,5)
        setPosition(binding.matchButton6,6)
        setPosition(binding.matchButton7,7)
        setPosition(binding.matchButton8,8)
        setPosition(binding.matchButton9,9)
        setPosition(binding.matchButton10,10)
        setPosition(binding.matchButton11,11)
        setPosition(binding.matchButton12,12)
    }

    fun setPosition(b: Button, pos: Int){
        b.visibility = View.VISIBLE  // or View.GONE if you want it to disappear entirely
        b.isClickable = true
        b.isEnabled = true
//        b.text = cardNames[numbers[pos-1]]
        b.text = ""
        b.setOnClickListener {
//            playSound(cardSounds[numbers[pos-1]])
            println("numbers[5]:" + numbers[pos-1])
//            println("cardList[0]:" + cardList[0])
//            println("WHATDOWEHAVE:"+cardNames[cardList[0]])
            if(!secondClicked) {
                if (buttonPosClicked == 0) {
                    //we are the first button clicked in this attempted pair. don't try to compare anything. flip card, save what it is, and return
                    buttonPosClicked = pos
                    buttonClickedID = b.id
                    b.text = cardNames[numbers[pos - 1]]
                } else { //we've clicked once. Evaluate and remove 2 cards, or flip both cards back
                    b.text = cardNames[numbers[pos - 1]]
                    secondClicked = true
                    if (b.text == cardNames[numbers[buttonPosClicked - 1]] && b.id != buttonClickedID) {
                        //choice is correct - disappear the card and other card and decrement the card count
//                    button.text = "test"

                        // Start a coroutine to delay for 2 seconds and then clear the text
                        CoroutineScope(Dispatchers.Main).launch {
                            delay(1000L) // Delay for 2 seconds (2000 milliseconds)
                            b.text = "" // Clear the text
                            cardCount -= 2
                            b.visibility = View.INVISIBLE
                            b.isClickable = false
                            view?.findViewById<Button>(buttonClickedID)?.let {
                                // Change the button attributes here
//                        it.text = "Updated Button"
                                it.isEnabled = false // Example: make it unclickable
                                it.visibility = View.INVISIBLE
                                it.isClickable = false
                            }
                            if (cardCount <= 0) {
                                resetCards()
                            }
                            secondClicked = false
                        }
                    } else {
                        //flip back
                        // Start a coroutine to delay for 2 seconds and then clear the text
                        CoroutineScope(Dispatchers.Main).launch {
                            delay(1000L) // Delay for 2 seconds (2000 milliseconds)
                            b.text = ""
                            view?.findViewById<Button>(buttonClickedID)?.let {
                                // Change the button attributes here
                                it.text = ""
                            }
                            secondClicked = false
                        }
                    }
                    buttonPosClicked = 0
                }
                playSound(cardSounds[numbers[pos-1]])
            }
        }
    }


}