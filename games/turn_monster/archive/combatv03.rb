#testing combat engine

#define global variables
$dmg = 0
$hit = 0

#define monster stat arrays
mname = ["Ian", "Alison", "Brian", "Lisa", "Jaime", "Amelia"]
mtype = ["Orc", "Troll", "Dragon"]
ptype = ["Brave", "Bold", "Fearless", "Destroyer"]
hp = [100, 150, 200]
health = []

#MODULES
#module to print class instance variables
module Report
  def reportstats
  puts "#{@name} the #{@race} has #{@hp} health."  
  end
  def repdmginc
    puts "#{@name} has taken #{$dmg} damage."
    puts "#{@name} now has #{@hp} hit points."
  end
  def repdmgout
    puts "#{@name} has done #{$dmg} damage."
  end
end

#module for combat interactions
module Combat
  def rollforhit
    roll = rand(1..10)
    if roll > 5                       #later to include conditions for monster level/armor 
        @hit = 1
    else @hit = 0
    end
    $hit = @hit
  end
  def rephit
      puts "#{@hit}"
  end
  def rollfordmg
      roll = rand(1..100)
      $dmg = roll
  end
  def repdmg
        puts "#{$dmg}"
  end
  def hpdec
    @hp = @hp -= $dmg
  end
  def hitcheck
    if @hit == 1
      puts "#{@name} has hit!"
    elsif @hit == 0 
      puts "#{@name} has missed!"
    end
    puts @hit
    puts $hit
  end
end

#CLASSES
#define player class
class Player
  include Report
  include Combat
  attr_accessor :name
  attr_accessor :race
  attr_accessor :hp
  attr_accessor :hit
  def initialize(name, race, hp, hit= 0)
    @name = name
    @race = race
    @hp = hp
    @hit = hit
  end
end
#define monster class
class Monster
    include Report
    include Combat
    attr_accessor :name
    attr_accessor :type
    attr_accessor :hp
    attr_accessor :hit
  def initialize (name, race, hp, hit= 0)
    @name = name
    @race = race
    @hp = hp
    @hit = hit
  end
end
#define class for loot
class Loot
  attr_accessor :slot
  attr_accessor :strength
  attr_accessor :mana
  attr_accessor :health
  def initialize(slot, strength, mana, health)
    @slot = slot
    @strength = strength
    @mana = mana
    @health = health
  end
  def report
    puts "Slot: #{@slot}"
    puts "Strength: #{strength}"
    puts "Mana: #{@mana}"
    puts "Health: #{health}"
  end
end

#start program

#create player
puts "Enter your name, Hero! :"
$pname = gets.chomp.capitalize
$ptype = ptype.sample
player1=Player.new($pname, $ptype, 100)

#create monster
name2 = mname.sample
type2 = mtype.sample
if type2 == mtype[0]
    hp = hp[0]
elsif type2 == mtype[1]
    hp = hp[1]
else 
    hp = hp[2]
end
monster1=Monster.new(name2, type2, hp)


#Begin
player1.reportstats
sleep 1
monster1.reportstats
sleep 1

#player turn
player1.rollforhit
player1.hitcheck
player1.rollfordmg
player1.repdmgout
monster1.hpdec
sleep 1
monster1.reportstats
sleep 1

#monster turn
monster1.rollforhit
monster1.hitcheck
monster1.rollfordmg
monster1.repdmgout
player1.hpdec
sleep 1
player1.reportstats
sleep 1

#create loot
loot1=Loot.new(rand(1..100), rand(1..100),rand(1..100),rand(1..100))
loot1.report
puts player1.hp








#monster1.rollforhit
#monster1.rephit
#monster1.rollfordmg
#monster1.repdmg
#loot1=Loot.new(100, 100, 100, 100)
#loot1.report
#end program

