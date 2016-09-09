# require_relative('player')
# require_relative('interface')
# require_relative('dice')
# require_relative('loot')
# require_relative('game')

class Monster
  attr_accessor :health
  attr_accessor :name
  attr_accessor :type
  attr_accessor :attack
  attr_accessor :damage
  attr_accessor :turn_count
  
  def initialize(name, type)
    # binding.pry
    @health = type['health']
    @name = name
    @type = type['type']
    @attack = 0
    @damage = 0
    @turn_count = 0 
  end  

end