# Turn Monster!

#define global variables
$attack = 0
$monsterhp = 100
$playerhp = 100
$roll = 0

#define class for player
class Player
	
#set player attributes and assign to variables
		def initialize(health, name, type)
		@health = health
		@name = name.to_sym
		@type = type.to_sym
		end
	
#define methods report on player status
	def preport
		puts "#@name the #@type with #@health health has looted an item!"
		puts " "
	end

	def nreport
		puts "No item has dropped for #@name the #@type! :("
		puts " "
	end
	
	def death
		puts "#@name has died! :("
	end
	
	def defreport
		puts "#@name has taken #$attack damage and now has #@health health"
		puts ""
		sleep 1
	end
	
#define player attack methods
	def attack
		puts "#@name the #@type attacks!"
		sleep 1
		$attack = rand(1..100)
	end
	
	def defend
		@health -= $attack
		$playerhp -= $attack
		sleep 1
	end
				
end

#define class for monster
class Monster
	
	def initialize(health, name, type)
		@health = health
		@name = name
		@type = type
		@name.to_s
		@type.to_s

		
	end
	
#define methods report on monster status		
	def mreport
		system ("clear")
		puts "#@name the #@type approaches!!!"
		puts " "
		sleep 2
		puts "#@name has #@health health"
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
system ("clear")
sleep 1
puts "Welcome to Turn Monster!"
puts " "
sleep 2
puts "What is your name?"
pname = gets.chomp
sleep 1
puts "What is your class? (Mage or Warrior)"
ptype = gets.chomp
sleep 1

#define array of monster names and types
mnames = ["Bob", "Rita", "Sue"]
mtypes = ["Troll", "Dragon", "Orc"]

#select monster name and type at random
mname = mnames.sample(1)
mtype = mtypes.sample(1)
mstats = Hash.new
mstats = { mname => mtype}

#create player
player1=Player.new(100, pname, ptype)

#create monster
monster1=Monster.new(100, mname, mtype)
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
	sleep 2
	$roll = rand(1..100)
	puts "You rolled a #$roll"
	sleep 1 

if $roll > 10 then
	looted1=Looted.new(rand(1..60),rand(1..10),rand(1..10),rand(1..10),rand(1..10))
	player1.preport
	looted1.loot
	looted1.report

elsif $roll < 10 then
	player1.nreport
	sleep 1
end

break

end

if $playerhp <=0
	player1.death
	sleep 1
	
break

end

end

#ta-da!




	




