# require_relative('monster')
# require_relative('interface')
# require_relative('loot')
# require_relative('player')
# require_relative('game')

#define class for player
class Player 
    attr_accessor :health
    attr_accessor :name
    attr_accessor :type
    attr_accessor :xp
    attr_accessor :inventory
    attr_accessor :equipped
    attr_accessor :damage
    attr_accessor :wins
    attr_accessor :mana
    attr_accessor :level

    def initialize(name, health, mana, type, xp)
    @health = health
    @name = name
    @mana = mana
    @type = type
    @xp = xp
    @inventory = Array.new
    @equipped = Array.new
    @level = 1
    @damage = 0
    @wins = 0
    @def_rating = 0
    @hit_rating = 0
    end

    def reset
        @health = 100
        @mana = 100
    end

end