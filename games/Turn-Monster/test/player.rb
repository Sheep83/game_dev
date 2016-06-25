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
    attr_accessor :mana_ok
    attr_accessor :mana_cost
    attr_accessor :turn_count


    def initialize(name, health, mana, type, xp, runner)
    @health = health
    @name = name
    @mana = mana
    @mana_ok = false
    @mana_cost = 0
    @type = type
    @xp = xp
    @inventory = Array.new
    @equipped = Array.new
    @level = 1
    @damage = 0
    @wins = 0
    @def_rating = 0
    @hit_rating = 0
    @turn_count = 0
    @runner = runner
    end

    def reset
        @health = 100
        @mana = 100
    end

    def xpinc
      @xp += 50
    end

    def mana_check
        if @mana >= @mana_cost
        @mana_ok = true
        else @mana_ok = false 
        end
    end

    def save(player)
        sql = "INSERT INTO players (name, health, mana, level, xp) VALUES ('#{@name}', '#{@health}', '#{@mana}', #{@level}, '#{@xp}') RETURNING *"
        result = @runner.run(sql)
        binding.pry
    end


end