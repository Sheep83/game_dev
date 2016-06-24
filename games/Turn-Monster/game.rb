require('pry-byebug')

require_relative('interface')
require_relative('player')
require_relative('monster')
require_relative('dice')
require_relative('loot')


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
    attacker.damage = rand(50..75)
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

  def run(dice)
    loot_type = [ 'Sword', 'Staff', 'Codex', 'Robes', 'Cloak']
    loot_enchant = ['Fire', 'Ice', 'Focus', 'Vitality', 'Mana']
    $game_state = 1
    unless $game_state == 0
    interface.mreport(monster)
      loop do
        if player.health > 0
        interface.choose_attack(player)
        choice = gets.chomp
        case when choice == "1"
        interface.attack_init(player, monster)
        dice.roll_for_hit(100)
        # binding.pry
        if dice.hit == true
          interface.hit(player, monster)
          attack(player, monster)
        else interface.no_hit(player, monster)
        end
        end
        case when choice == "2"
        interface.attack_init(player, monster)
        dice.roll_for_hit(100)
        # binding.pry
        if dice.hit == true
          interface.hit(player, monster)
          fireball(player, monster)
        else player.mana -= 60
          interface.no_hit(player, monster)
        end
        end
        case when choice == "3"
        interface.attack_init(player, monster)
        dice.roll_for_hit(100)
        # binding.pry
        if dice.hit == true
          interface.hit(player, monster)
          blizzard(player, monster)
        else player.mana -= 80
          interface.no_hit(player, monster)
        end
        end
        else
        interface.death_report(player)
        interface.finalrep(player)
        break
        end
        if monster.health > 0 
        interface.attack_init(monster, player)
        dice.roll_for_hit(100)
        # binding.pry
        if dice.hit == true
          interface.hit(monster, player)
          monster_attack(monster, player)
        else monster.damage = 0
          interface.no_hit(monster, player)
        end
        interface.def_report(monster, player)
        elsif monster.health <= 0
        interface.win(player, monster)
        xpinc(player)
        dice.roll_for_loot(100)
        # binding.pry
        if dice.looted == true
          loot = Loot.new(loot_type.sample, loot_enchant.sample)
          # binding.pry
          player.inventory.push(loot)
          interface.loot_report(loot)
        else interface.no_loot_report(player)
        end 
        interface.finalrep(player)
        break
        end
      end ####loop end
    end ####unless end
  end ###def end

end ###class end
  
  $game_state = 1
  dice = Dice.new(100)
  interface = Input.new()
  interface.title
  interface.player_init
  player = Player.new(interface.player_name, 100, 100, interface.player_type, 0)
  while $game_state == 1
  interface.monster_init
  monster = Monster.new(interface.monster_name, interface.monster_type)
  player.reset
  game = Game.new(player, monster, interface, dice)
  game.run(dice)
  puts "Play again (y/n)?"
  yn = gets.chomp
    if yn == "n"
    $game_state = 0
    else 
    $game_state = 1
    end
  end













