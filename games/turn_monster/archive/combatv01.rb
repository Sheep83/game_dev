#testing combat engine
$damage = 0

#module to print class instance variable
module Report
  def report
  puts "#{@name}"  
  puts "#{@race}"
  end
end

#module to roll for hit and damage
module Attack
  def rollforhit
    roll = rand(1..10)
    sleep 1
    puts "you have rolled #{roll}"
    if roll > 2                       #later to include conditions for monster level/armor 
      puts "you have hit your enemy"
      sleep 1
      puts "rolling for damage"
      dmg = rand(1..10)
      $dmg = dmg
      puts "you have done #{dmg} damage to your enemy"
    else puts "you have missed your enemy"
    end
  end
end

#module to declinate health class instance variable
module Defend
  def hpdec
    @hp -= $dmg
    puts "you have taken #{$dmg} damage and now have #{@hp} health"
  end
end


#define player class
class Player
  include Report
  include Attack
  include Defend
  
  attr_accessor :name
  attr_accessor :race

  def initialize(name, race, hp)
    @name = name
    @race = race
    @hp = hp
  end
end




player1=Player.new("Brian", "Elf", 100)
player1.report
player1.rollforhit
player1.hpdec
player1.hpdec

