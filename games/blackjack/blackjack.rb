require'pry-byebug'


values = [2, 3, 4, 5, 6, 7, 8, 9, 10, 'J', 'Q', 'K', 'A']
suits = ['hearts', 'diamonds', 'clubs', 'spades']
deck = []

class Card_Deck
attr_accessor :values
attr_accessor :suits
attr_accessor :deck
  def initialize(values, suits, deck)
    @values = values
    @suits = suits
    @deck = deck
  end

  def create
    @values.each_with_index do |v, i|
    @suits.each do |s|
    @deck.push({
        score: i,
        value: v,
        suit: s,
      })
    end
    end
  end

end

deck_of_cards = Card_Deck.new(values, suits, deck)
deck_of_cards.create
# binding.pry
return @deck
# binding.pry

#access the deck using deck_of_cards.deck[index]

  
# deck_of_cards.each do | x|

#   puts "Card :" + x[:value] + x[:suit]
#   sleep 0.5
#   end

