# Turn Monster!

#define global variables
onoff = [0, 1]
$attack = 0
$monsterhp = 0
$playerhp = 100
$roll = 0
$enginestate = onoff[0]


#define class for player
class Player
	
#set player attributes and assign to variables
		def initialize(health, name, type, xp)
		@health = health
		@name = name
		@type = type
		@xp = xp
		end
	
#define methods report on player status
	def preport
		puts "#@name the #@type with #@health health has found an item and now has #@xp XP!"
		sleep 1
		puts " "
	end

	def nreport
		puts "No item has dropped for #@name the #@type! :("
		puts " "
	end
	
	def death
		puts "#@name has died! :("
		puts "Play again (y/n)?"
		repeat = gets.chomp
		if repeat == "n"
		$enginestate = 0
		elsif repeat == "y"
		$enginestate = 1
		end
	end
	
	def defreport
		puts "#@name has taken #$attack damage and now has #@health health"
		puts ""
		sleep 1
	end
	
#define player attack methods
	def attack
		puts "#@name the #@type attacks!"
		puts " "
		sleep 1
		$attack = rand(1..100)
	end
	
	def defend
		@health -= $attack
		$playerhp -= $attack
		sleep 1
	end
#define method to increment player xp
	def xpinc
		@xp += 50
	end

end

#define class for monster
class Monster
	
	def initialize(health, name, type)
		@health = health
		@name = name
		@type = type	
	end
	
#define methods report on monster status		
	def mreport
		system ("clear")
		puts "#@name" + " the " + "#@type " + "approaches!!"
		puts " "
		puts "#@name" + " has " + "#@health " + "health"
		puts " "
		sleep 2
	end
	
	def defreport
		puts "#@name has taken #$attack damage and now has #@health health"
		puts ""
		sleep 1
	end
	
#define monster attack methods	
	def attack
		puts "#@name attacks the player!"
		puts " "
		sleep 1
		$attack = rand(1..100)
	end
		
	def defend
		@health -= $attack
		$monsterhp -= $attack
		sleep 1
	end
		
end
			
#define class for looted item
class Looted
	@@item_number = 0
	
#set item attributes and assign to variable
	def initialize(level, strength, agility, health, mana)
		@level = level
		@strength = strength
		@agility = agility
		@health = health
		@mana = mana
	end 

#define method to output stats
	def report
		puts "Item Number #@@item_number"
		puts "Level : #@level"
		puts "Strength = #@strength"
		puts "Agility = #@agility"
		puts "Health = #@health"
		puts "Mana = #@mana"
		puts" "
	end
	
#define method to increase item number
	def loot
	@@item_number += 1
	end
	
end









#start program!
$enginestate = onoff[1]
unless $enginestate == onoff[0]
system ("clear")
sleep 1
puts "Welcome to Turn Monster!"
puts " "
sleep 1
puts "What is your name?"
pname = gets.chomp.capitalize
sleep 1
puts "What is your class? (Mage or Warrior)"
ptype = gets.chomp.capitalize
sleep 1

#define array of monster names and types
mnames = ['Bob', 'Rita', 'Sue']
mtypes = ['Orc', 'Troll', 'Dragon']
mhealth = [100, 150, 200]
#select monster name and type at random
mname = mnames.sample
mtype = mtypes.sample

if mtype == mtypes[0]
monsterhp = mhealth[0]
$monsterhp = mhealth[0]
elsif mtype == mtypes[1]
monsterhp = mhealth[1]
$monsterhp = mhealth[1]
elsif mtype == mtypes[2]
monsterhp = mhealth[2]
$monsterhp = mhealth[2]
end

#add monsterstats to array
mstats = [ monsterhp, mname, mtype ] 

#create player
player1=Player.new(100, pname, ptype, 0)

#create monster
monster1=Monster.new(monsterhp, mname, mtype)
sleep 1
monster1.mreport
sleep 1


#game engine loop
loop do
if $playerhp > 0
#player attack turn
	player1.attack
	monster1.defend
	monster1.defreport

elsif playerhp <=0 
	player1.death
	sleep 1

break

end

if 	$monsterhp > 0
#monster attack turn
	monster1.attack
	player1.defend
	sleep 1
	player1.defreport

elsif $monsterhp <= 0
	puts "You won!"
	puts " "
	sleep 2
	$roll = rand(1..100)
	puts "You rolled a #$roll"
	puts " "
	sleep 1 

if $roll > 10 then
	looted1=Looted.new(rand(1..60),rand(1..10),rand(1..10),rand(1..10),rand(1..10))
	player1.xpinc
	player1.preport
	looted1.loot
	looted1.report
	puts "Play again (y/n)?"

elsif $roll < 10 then
	player1.nreport
	sleep 1
	puts "Play again (y/n)?"
end


break

end

if $playerhp <=0
	player1.death
	sleep 1
	puts "Play again (y/n)?"
	
break

end

end

end

#ta-da!




	




