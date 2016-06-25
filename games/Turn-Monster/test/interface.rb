require_relative('player')
require_relative('monster')
require_relative('loot')
# require_relative('game')
# require_relative('dice')

class Input

attr_accessor :player_name
attr_accessor :player_type
attr_accessor :monster_name
attr_accessor :monster_type

def title
  system("clear")
  5.times do 
  puts "Welcome to Turn Monster!"
  puts "      .     _///_,"
  puts "  .      / ` ' '>"
  puts "    )   o'  __/_'>"
  puts "   (   /  _/  )_\'>"
  puts "    ' '__/   /_/\_>"
  puts "        ____/_/_/_/"
  puts "       /,---, _/ /"
  puts "      ''  /_/_/_/"
  puts "         /_(_(_(_ "
  sleep 0.5
  system("clear")
  puts "Welcome to Turn Monster!"
  puts "      .     _///_,"
  puts "  .      / ` ' '>"
  puts "    (   o'  __/_'>"
  puts "    )  /  _/  )_\'>"
  puts "    ' '__/   /_/\_>"
  puts "        ____/_/_/_/"
  puts "       /,---, _/ /"
  puts "      ''  /_/_/_/"
  puts "         /_(_(_(_ "
  sleep 0.5
  system ("clear") 
  end
end

  def mreport(monster)
    system ("clear")
    puts "#{monster.name} the #{monster.type} approaches!!"
    puts " "
    puts "#{monster.name} has #{monster.health} health"
    puts " "
    sleep 2
    return
  end
  
  # 

  def def_report(attacker, target)
    puts "#{target.name} has taken #{attacker.damage} damage and now has #{target.health} health."
    puts ""
    sleep 2
    return
  end

  def loot_report(player, loot)
    puts "#{player.name} has found an item!"
    puts " "
    puts "You have picked up the #{loot.type} of #{loot.enchant}!"
    puts " "
    sleep 1
    puts "Level : #{player.level}"
    puts "Focus = #{loot.focus}"
    puts "Mana = #{loot.mana}"
    puts "Health = #{loot.health}"
    puts" "
  end
  
  def death_report (player)
    puts "#{player.name} has been slain!"
    puts " "
  end
  
  def finalrep(player)
    puts "#{player.name} has #{player.wins} wins and #{player.inventory.length} items equipped."
    puts " "
    sleep 1
    puts "Your current level is #{player.level} and you have #{player.xp} xp!"
  end

  def attack_init(attacker, target)
    system("clear")
    puts "#{attacker.name} attacks #{target.name}!"
    puts " "
    sleep 1
  end

  def defend
    @health -= $attack
  end

  def win (player, monster)
    player.wins += 1
    puts "#{player.name} has slain #{monster.name} the #{monster.type}! :)"
    puts " "
  end

  def choose_attack(player)
    system("clear")
    puts "Choose your attack!!!"
    puts
    puts "You have #{player.health} health and #{player.mana} mana."
    puts
    puts "1 - Weapon Attack"
    puts "2 - Fireball - 60 mana"
    puts "3 - Ice Blizzard - 80 mana"
  end

  def turn_end_report(player, monster)
    system("clear")
    puts "#{player.name} the #{player.type} has #{player.health} health and #{player.mana} mana."
    sleep 1
    puts "#{monster.name} the #{monster.type} has #{monster.health} health."
    sleep 1
  end

  def player_init
    puts "What is your name?"
    @player_name = gets.chomp.capitalize
    puts "What is your class? (Mage or Warrior)"
    @player_type = gets.chomp.capitalize
  end

  def monster_init
    monster_names = ['Sandy', 'Theo', 'Zsolt', 'Jay', 'Val', 'Tino', 'Harvey']
    monster_types = [{'type' => 'Orc', 'health' => 100},
                    {'type' => 'Troll', 'health' => 150},
                    {'type' => 'Dragon', 'health' => 200}
                  ]
    @monster_name = monster_names.sample
    @monster_type = monster_types.sample
  end

  def hit(attacker, target)
    puts "#{attacker.name} scores a hit on #{target.name}!"
    sleep 1
  end

  def no_hit(attacker, target)
    puts "#{attacker.name} misses #{target.name}"
    sleep 1
  end

  def loot_report(loot)
    puts "You have looted the #{loot.type} of #{loot.enchant}!"
    puts " "
    sleep 1
    # puts "Level : #@level"
    puts "Type = #{loot.type}"
    puts "Enchant - #{loot.enchant}"
    # puts "Health = #@health"
    # puts "Mana = #@mana"
    puts" "
    return
  end

  def no_loot_report(player)
    puts "No item has dropped for #{player.name}! :("
    puts " "
  end

  def no_mana(player)
    sleep 1
    puts "#{player.name} has insufficient mana."
    puts
    sleep 1 
  end

  def save_player(player)
    puts "Would you like to save the player's progress?"
    yn = gets.chomp
    if yn == "y"
    player.save(player)
    else 
    # $game_state = 1
    end
  end

end


  

