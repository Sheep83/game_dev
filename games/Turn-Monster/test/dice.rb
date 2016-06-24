# require_relative('player')
# require_relative('monster')
# require_relative('loot')
# require_relative('game')
# require_relative('interface')

class Dice

  attr_accessor :roll
  attr_accessor :sides
  attr_accessor :hit
  attr_accessor :looted


  def initialize(sides)
    @sides = sides
    @roll = 0
    @looted = false
    @hit
  end

  def roll_for_stats
    @roll = rand(1..9)
  end

  def roll_for_hit(sides)
    @roll = rand(1..sides)
    if @roll <= 40
      @hit = true
    else @hit = false
    end
  end

  def roll_for_damage(sides)
    @roll = rand(1..sides)
  end

  def roll_for_loot(sides)
    @roll = rand(1..sides)
    if @roll <=50
      @looted = true
    else
      @looted = false
    end
  end
end
