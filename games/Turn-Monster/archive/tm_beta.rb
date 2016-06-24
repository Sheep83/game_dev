# Turn Monster!
#beta v0.1

#define global variables
$attack = 0
$roll = 0
$enginestate = 0
$wins = 0
$items = 0

#define class for player
class Player
		attr_accessor :health
		attr_accessor :name
		attr_accessor :type
		attr_accessor :xp
#set player attributes and assign to variables
		def initialize(health, name, type, xp)
		@health = health
		@name = name
		@type = type
		@xp = xp
		end
#define methods to report on player status
	def preport
		puts "#@name the #@type with #@health health has found an item and now has #@xp XP!"
		puts " "
	end
	def nreport
		puts "No item has dropped for #@name the #@type! :("
		puts " "
	end
#define methods to restart or quit
	def death
		puts "#@name has died!"
		puts " "
	end
	def finalrep
		puts "You have #{$wins} wins and have looted #{$items} items."
		puts " "
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
		$attack = rand(10..66)
	end
	def defend
		@health -= $attack
	end
#define method to increment player xp
	def xpinc
		@xp += 50
	end
	def win
		$wins += 1
		puts "You won! :)"
		puts " "
	end
end

#define class for monster
class Monster
	attr_accessor :health
	attr_accessor :name
	attr_accessor :type
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
		sleep 1
		return
	end
	def defreport
		puts "#@name has taken #$attack damage and now has #@health health"
		puts ""
		sleep 1
		return
	end
#define monster attack methods	
	def attack
		puts "#@name attacks the player!"
		puts " "
		sleep 1
		$attack = rand(1..66)
		return
	end
	def defend
		@health -= $attack
		$monsterhp -= $attack
		return
	end
end
			
#define class for looted item
class Looted
	attr_accessor :item_number
	@@item_number = 0
#set item attributes and assign to variable
	def initialize(name, type, level, strength, agility, health, mana)
		@name = name
		@type = type
		@level = level
		@strength = strength
		@agility = agility
		@health = health
		@mana = mana
	end 
#define method to output stats
	def report
		puts "You have picked up the #{@name} of #{@type}!"
		puts " "
		sleep 1
		puts "Item Number #@@item_number"
		puts "Level : #@level"
		puts "Strength = #@strength"
		puts "Agility = #@agility"
		puts "Health = #@health"
		puts "Mana = #@mana"
		puts" "
		return
	end
#define method to increase item number
	def loot
	@@item_number += 1
	$items = @@item_number
	return
	end
end

#define procedures for game engine
start = proc {
unless $enginestate == 0
system ("clear")
sleep 1
puts "Welcome to Turn Monster!"
puts " "
sleep 1
puts "What is your name?"
pname = gets.chomp.capitalize
puts "What is your class? (Mage or Warrior)"
ptype = gets.chomp.capitalize
#define array of monster names and types
mnames = ['Dad', 'Mum', 'Brian', 'Lisa', 'Jaime', 'Amelia', 'Theo']
mtypes = ['Orc', 'Troll', 'Dragon']
mhealth = [100, 150, 200]
lnames = ['Sword', 'Staff']
ltypes = ['Fire', 'Strength']
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
#create player
player1=Player.new(100, pname, ptype, 0)
#create monster
monster1=Monster.new(monsterhp, mname, mtype)
sleep 1
monster1.mreport
sleep 1
#game engine loop
loop do
	if player1.health > 0
#player attack turn
	player1.attack
	monster1.defend
	monster1.defreport
	elsif player1.health <= 0
	player1.death
	player1.finalrep
	break
	end
	if monster1.health > 0
#monster attack turn
		monster1.attack
		player1.defend
		player1.defreport
	elsif monster1.health <= 0
		player1.win
		$roll = rand(1..100)
			if $roll > 50 then
			looted1=Looted.new(lnames.sample, ltypes.sample, rand(1..60),rand(1..10),rand(1..10),rand(1..10),rand(1..10))
			player1.xpinc
			player1.preport
			looted1.loot
			looted1.report
			player1.finalrep
			break
			else
			player1.nreport
			player1.finalrep
			puts " "
			sleep 1
			break
			end
	end
end
end}

#start program!
$enginestate = 1
while $enginestate == 1
start.call
puts "Play again (y/n)?"
yn = gets.chomp
	if yn == "n"
		$enginestate = 0
	else 
	 	$enginestate = 1
	end	
end
#ta-da!




	


