require('pry-byebug')

require_relative('interface')
require_relative('player')
require_relative('monster')
require_relative('dice')
require_relative('loot')
require_relative( 'db/sql_runner' )


$game_state = 0

class Game
  attr_accessor :player
  attr_accessor :monster
  attr_accessor :interface
  attr_accessor :loot_type
  attr_accessor :loot_enchant
  
  def initialize(player, monster, interface, dice)
    @player = player
    @monster = monster
    @interface  = interface
    @dice = dice
  end

  def attack(attacker, target) 
    attacker.damage = rand(10..25)
    if target.health < attacker.damage
    attacker.damage = target.health
    target.health -= attacker.damage
    else
    target.health -= attacker.damage
    end
    interface.def_report(attacker, target)
  end

  def monster_attack(attacker, target) 
    attacker.damage = rand(10..25)
    if target.health < attacker.damage
    attacker.damage = target.health
    target.health -= attacker.damage
    else
    target.health -= attacker.damage
    end
    # interface.def_report(attacker, target)
  end

  def fireball(attacker, monster)
    attacker.mana -= 60
    attacker.damage = rand(50..75)
    if monster.health < attacker.damage
    attacker.damage = monster.health
    monster.health -= attacker.damage
    else
    monster.health -= attacker.damage
    end
    interface.def_report(attacker, monster)
  end

  def blizzard(attacker, monster)
    attacker.mana -= 80
    attacker.damage = rand(70..90)
    if monster.health < attacker.damage
    attacker.damage = monster.health
    monster.health -= attacker.damage
    else
    monster.health -= attacker.damage
    end
    interface.def_report(attacker, monster)
  end

  def xpinc(player)
    player.xp += 50
  end

  def player_turn(player, monster, dice)
    if player.health > 0
    loop do 
      interface.choose_attack(player)
      choice = gets.chomp
      case when choice == "1"
      player.mana_cost = 0
      # binding.pry
      interface.attack_init(player, monster)
      dice.roll_for_hit(100)
        if dice.hit == true
        interface.hit(player, monster)
        attack(player, monster)
        player.turn_count += 1
        player.mana += 10
        elsif dice.hit == false
        interface.no_hit(player, monster)
        player.turn_count += 1
        player.mana += 10
        end#end of dice if
      end#end of case
      case when choice == "2"
      player.mana_cost = 60
      # binding.pry
      player.mana_check
      if player.mana_ok == true
      interface.attack_init(player, monster)
      # binding.pry
      dice.roll_for_hit(100)
        if dice.hit == true
        interface.hit(player, monster)
        fireball(player, monster)
        player.turn_count += 1
        player.mana += 10
        else player.mana -= 60
        interface.no_hit(player, monster)
        player.turn_count += 1
        player.mana += 10
        end##dice if end
      elsif player.mana_ok == false
      interface.no_mana(player)
      end
      end###case end
      case when choice == "3"
      player.mana_cost = 80
      # binding.pry
      player.mana_check
        if player.mana_ok == true
        interface.attack_init(player, monster)
        dice.roll_for_hit(100)
          if dice.hit == true
          interface.hit(player, monster)
          blizzard(player, monster)
          player.turn_count += 1
          player.mana += 10
          else player.mana -= 80
          interface.no_hit(player, monster)
          player.turn_count += 1
          player.mana += 10
          end   #dice hit end
        elsif player.mana_ok == false
        interface.no_mana(player)
        end    #mana check end
      end #case end
    break if 
      player.turn_count > monster.turn_count
    end 
    end  
end         

  def monster_turn(monster, player, dice)
    if monster.health > 0 
    interface.attack_init(monster, player)
    dice.roll_for_hit(100)
      if dice.hit == true
      interface.hit(monster, player)
      monster_attack(monster, player)
      monster.turn_count += 1
      else monster.damage = 0
      interface.no_hit(monster, player)
      monster.turn_count += 1
      end
    elsif monster.health <= 0
    end
  end
end
  
loot_type = [ 'Sword', 'Staff', 'Codex', 'Robes', 'Cloak']
loot_enchant = ['Fire', 'Ice', 'Focus', 'Vitality', 'Mana']  
runner = SqlRunner.new({dbname: 'tm_test', host: 'localhost'})
dice = Dice.new(100)
interface = Input.new()
interface.title
interface.player_init
player = Player.new(interface.player_name, 100, 100, interface.player_type, 0, runner)
interface.save_player(player)
interface.monster_init  
monster = Monster.new(interface.monster_name, interface.monster_type)
game = Game.new(player, monster, interface, dice)
$game_state = 1
while $game_state == 1
interface.monster_init  
monster = Monster.new(interface.monster_name, interface.monster_type)
interface.mreport(monster)
player.reset
  loop do 
    if player.health > 0
    game.player_turn(player, monster, dice)
    elsif player.health <= 0
    interface.death_report(player)
    interface.finalrep(player)
    break
    end
    if monster.health > 0
    game.monster_turn(monster, player, dice)
    elsif monster.health <= 0
    interface.win(player, monster)
    player.xpinc
    dice.roll_for_loot(100)
    if dice.looted == true
    loot = Loot.new(loot_type.sample, loot_enchant.sample)
    player.inventory.push(loot)
    interface.loot_report(loot)
    interface.finalrep(player)
    break
    else interface.no_loot_report(player)
    interface.finalrep(player)
    sleep 1
    break
    end
    end
  end
puts "Play again (y/n)?"
yn = gets.chomp
  if yn == "n"
    $game_state = 0
  else 
    $game_state = 1
  end 
end














