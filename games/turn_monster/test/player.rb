require_relative('monster')
require_relative('interface')
require_relative('loot')
require_relative('player')
require_relative('game')
require_relative( 'db/sql_runner' )


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


    def initialize(options, runner)
    # @id = options['id'].to_i
    @health = options['health']
    @name = options['name']
    @mana = options['mana']
    @type = options['type']
    @xp = options['xp']
    @level = options['level']
    @mana_ok = false
    @mana_cost = 0
    @inventory = Array.new
    @equipped = Array.new
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

    def save()
        sql = "INSERT INTO players (name, type, level, xp) VALUES ('#{@name}', '#{@type}', #{@level}, '#{@xp}') RETURNING *"
        result = @runner.run(sql)
        # binding.pry
    end

    # def load(player)
    #     sql = "SELECT * FROM players RETURNING *"
    #     result = @runner.run(sql)
    #     player_data = result.first
    #     $player = Player.new(player_data)
    # end

    def self.map_items(sql, runner)
        players = runner.run(sql)
        binding.pry
        $player = Player.new(players[0], runner)
        binding.pry
      end

      def self.map_item(sql, runner)
        result = Player.map_items(sql, runner)
        return result.first
      end

      def self.all(runner)
        sql = "SELECT * FROM players"
        return Player.map_items(sql, runner)
        # binding.pry
      end
end