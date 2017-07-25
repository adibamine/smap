import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'
import { Link } from 'react-router';

export default class InfluencersTable extends React.Component {
    constructor(props) {
        super(props)
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this)
    }


    render() {
        return  <table className="table table-stripped table-bordered">
            <thead>
            <tr>
                <th>User</th>
                <th>Nb commentaires</th>
            </tr>
            </thead>
            <tbody>
            {
                this.props.influencers.map(influencer =>
                    <tr key={influencer.get('user_id')}>
                        <td>
                            {influencer.get('user_name')}
                        </td>
                        <td>
                            <Link to={"/comments/" + influencer.get('client_id') + "/" + influencer.get('user_id')}><strong>{influencer.get('count')}</strong></Link>
                        </td>
                    </tr>
                )
            }

            </tbody>
        </table>

    }
}
