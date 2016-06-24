# require_relative('player')
# require_relative('monster')
# require_relative('loot')
# require_relative('interface')
# require_relative('game')

class Loot
  
  attr_accessor :slot
  attr_accessor :type
  attr_accessor :enchant
  
  def initialize(type, enchant)
    @type = type
    @enchant = enchant
    @slot = 0
  end 

end